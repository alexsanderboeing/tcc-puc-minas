package com.juntamedica.cep.service;

import com.juntamedica.cep.payload.CepResponse;

public interface CepService {

    CepResponse findCep(Long cep);
}
