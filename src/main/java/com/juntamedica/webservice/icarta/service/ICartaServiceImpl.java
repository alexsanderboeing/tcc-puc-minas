package com.juntamedica.webservice.icarta.service;

import com.juntamedica.processohistoriconotificacao.service.ProcessoHistoricoNotificacaoService;
import com.juntamedica.webservice.icarta.ICartaClient;
import com.juntamedica.webservice.icarta.enumerator.ICartaStatus;
import com.juntamedica.webservice.icarta.enumerator.ICartaStatusEmail;
import com.juntamedica.webservice.icarta.factory.ICartaFactory;
import com.juntamedica.webservice.icarta.payload.ICartaRequest;
import com.juntamedica.webservice.icarta.payload.ICartaResponse;
import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPRequest;
import com.juntamedica.webservice.icarta.payload.xml.ICartaSOAPResponse;
import com.juntamedica.webservice.icarta.payload.xml.responseelements.ICartaTackedEmailResponse;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class ICartaServiceImpl implements ICartaService {

    @Autowired
    private ICartaFactory iCartaFactory;

    @Autowired
    private ICartaClient iCartaClient;

    @Autowired
    private ProcessoHistoricoNotificacaoService processoHistoricoNotificacaoService;

    @Override
    public ICartaResponse sendEmail(ICartaRequest request) {
        ICartaSOAPRequest requestXML = iCartaFactory.buildRequestXML(request);
        ICartaSOAPResponse responseXML = null;

        try {
            responseXML = iCartaClient.sendTrackedEmail(requestXML);

            if (ICartaStatus.from(responseXML.getStatus()).compareTo(ICartaStatus.SUCESSO) != 0) {
                ICartaTackedEmailResponse responseStatusEmail = responseXML.getTrackedEmails() != null
                        && !responseXML.getTrackedEmails().getTrackedEmailResponse().isEmpty()
                        ? responseXML.getTrackedEmails().getTrackedEmailResponse().get(0)
                        : null;

                if (responseStatusEmail != null) {
                    if (ICartaStatusEmail.from(responseStatusEmail.getStatusEmail()).compareTo(ICartaStatusEmail.SUCESSO) != 0) {
                        throw new RuntimeException("Notificação " + responseStatusEmail.getMessageId() + " não enviada. "
                                + ICartaStatusEmail.from(responseStatusEmail.getStatusEmail()).getDescricao());
                    }
                }

                log.error("Evidência " + request.getMessageId() + " com erro: " + responseXML.getDescription());
                throw new RuntimeException(responseXML.getDescription());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return iCartaFactory.buildResponse(responseXML);
    }

    @Override
    @Transactional
    public void receiveICartaConfirmation(String messageId, String eventType, JSONObject bodyConfirmation) {
        if (eventType.toLowerCase().compareTo("evidencia") != 0) {
            log.info(String.format("O tipo de evento %s não é do tipo evidência!", eventType));
            return;
        }

        processoHistoricoNotificacaoService.updateNotificationWithICartaConfirmation(messageId, bodyConfirmation);
    }
}
