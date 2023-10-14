package com.juntamedica.variavel.service;

import com.juntamedica.variavel.VariavelRepository;
import com.juntamedica.variavel.factory.VariavelFactory;
import com.juntamedica.variavel.payload.VariavelResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class VariavelServiceImpl implements VariavelService {

    @Autowired
    private VariavelRepository variavelRepository;

    @Autowired
    private VariavelFactory variavelFactory;

    @Override
    public List<VariavelResponse> findAll() {
        return variavelFactory.buildResponseList(variavelRepository.findAll());
    }

    @Override
    public List<VariavelResponse> findAllLayout() {
        List<Long> ids = Arrays.asList(28L, 29L, 30L, 31L, 32L, 39L, 40L, 41L, 42L, 43L, 44L, 45L, 46L, 47L, 48L, 49L);
        return variavelFactory.buildResponseList(variavelRepository.findAllLayout(ids));
    }
}
