package com.juntamedica.webservice.viacep.service;

import com.juntamedica.webservice.viacep.payload.ViaCepResponse;

public interface ViaCepService {

    ViaCepResponse findCep(Long cep);
}
