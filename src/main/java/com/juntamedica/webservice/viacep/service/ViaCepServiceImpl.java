package com.juntamedica.webservice.viacep.service;

import com.juntamedica.webservice.viacep.exception.CepClientErrorException;
import com.juntamedica.cep.exception.CepNotFoundException;
import com.juntamedica.webservice.viacep.ViaCepClient;
import com.juntamedica.webservice.viacep.payload.ViaCepResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaCepServiceImpl implements ViaCepService {
    @Autowired
    private ViaCepClient viaCepClient;

    @Override
    public ViaCepResponse findCep(Long cep) {
        try {
            ViaCepResponse viaCepResponse = viaCepClient.findCep(cep);

            if (viaCepResponse.getErro() != null && viaCepResponse.getErro()) {
                throw new CepNotFoundException(cep);
            }

            return viaCepResponse;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new CepClientErrorException();
        }
    }
}
