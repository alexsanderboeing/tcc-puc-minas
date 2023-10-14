package com.juntamedica.variavel.service;

import com.juntamedica.variavel.payload.VariavelResponse;
import java.util.List;

public interface VariavelService {

    List<VariavelResponse> findAll();

    List<VariavelResponse> findAllLayout();
}
