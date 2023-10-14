package com.juntamedica.webservice.ibge.service;

import com.juntamedica.webservice.ibge.payload.IbgeEstadoResponse;
import com.juntamedica.webservice.ibge.payload.IbgeMunicipioResponse;
import java.util.List;

public interface IbgeService {

    List<IbgeEstadoResponse> findAllEstados();

    IbgeEstadoResponse findEstado(String uf);

    List<IbgeMunicipioResponse> findAllMunicipios();

    List<IbgeMunicipioResponse> findAllMunicipiosPorEstado(String uf);

    IbgeMunicipioResponse findMunicipio(String municipio);
}
