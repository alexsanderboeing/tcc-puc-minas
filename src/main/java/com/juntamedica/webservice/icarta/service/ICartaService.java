package com.juntamedica.webservice.icarta.service;

import com.juntamedica.webservice.icarta.payload.ICartaRequest;
import com.juntamedica.webservice.icarta.payload.ICartaResponse;
import net.minidev.json.JSONObject;

public interface ICartaService {

    ICartaResponse sendEmail(ICartaRequest request);

    void receiveICartaConfirmation(String messageId, String eventType, JSONObject bodyConfirmation);
}
