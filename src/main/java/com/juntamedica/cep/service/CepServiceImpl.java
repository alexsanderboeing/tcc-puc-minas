package com.juntamedica.cep.service;

import com.juntamedica.cep.Cep;
import com.juntamedica.cep.CepRepository;
import com.juntamedica.cep.factory.CepFactory;
import com.juntamedica.cep.payload.CepResponse;
import com.juntamedica.webservice.viacep.payload.ViaCepResponse;
import com.juntamedica.webservice.viacep.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class CepServiceImpl implements CepService {

    @Autowired
    private ViaCepService viaCepService;

    @Autowired
    private CepFactory cepFactory;

    @Autowired
    private CepRepository cepRepository;

    @Override
    public CepResponse findCep(Long cep) {
        Cep cepEntity = cepRepository.findByCodigo(cep);

        if (Objects.isNull(cepEntity)) {
            return cepFactory.buildResponse(this.createCep(cep));
        }

        return cepFactory.buildResponse(cepEntity);
    }

    private Cep createCep(Long cep) {
        ViaCepResponse viaCepResponse = viaCepService.findCep(cep);

        return cepRepository.save(cepFactory.build(viaCepResponse));
    }
}
