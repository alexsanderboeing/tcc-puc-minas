package com.juntamedica.webservice.ibge.service;

import com.juntamedica.webservice.ibge.IbgeClient;
import com.juntamedica.webservice.ibge.exception.IbgeClientErrorException;
import com.juntamedica.webservice.ibge.exception.IbgeEstadoNotFoundException;
import com.juntamedica.webservice.ibge.exception.IbgeMunicipioNotFoundException;
import com.juntamedica.webservice.ibge.payload.IbgeEstadoResponse;
import com.juntamedica.webservice.ibge.payload.IbgeMunicipioResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class IbgeServiceImpl implements IbgeService {

    @Autowired
    private IbgeClient ibgeClient;

    @Override
    public List<IbgeEstadoResponse> findAllEstados() {
        try {
            List<IbgeEstadoResponse> ibgeEstados = ibgeClient.findAllEstados();

            if (Objects.isNull(ibgeEstados) || ibgeEstados.isEmpty()) {
                throw new IbgeEstadoNotFoundException();
            }

            return ibgeEstados;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new IbgeClientErrorException();
        }
    }

    @Override
    public IbgeEstadoResponse findEstado(String uf) {
        try {
            IbgeEstadoResponse ibgeEstadoResponse = ibgeClient.findEstado(uf);

            if (Objects.isNull(ibgeEstadoResponse)) {
                throw new IbgeEstadoNotFoundException(uf);
            }

            return ibgeEstadoResponse;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new IbgeClientErrorException();
        }
    }

    @Override
    public List<IbgeMunicipioResponse> findAllMunicipios() {
        try {
            List<IbgeMunicipioResponse> ibgeMunicipios = ibgeClient.findAllMunicipios();

            if (Objects.isNull(ibgeMunicipios) || ibgeMunicipios.isEmpty()) {
                throw new IbgeMunicipioNotFoundException();
            }

            return ibgeMunicipios;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new IbgeClientErrorException();
        }
    }

    @Override
    public List<IbgeMunicipioResponse> findAllMunicipiosPorEstado(String uf) {
        try {
            List<IbgeMunicipioResponse> ibgeMunicipioList = ibgeClient.findAllMunicipiosPorEstado(uf);

            if (Objects.isNull(ibgeMunicipioList) || ibgeMunicipioList.isEmpty()) {
                throw new IbgeMunicipioNotFoundException(uf, "a UF");
            }

            return ibgeMunicipioList;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new IbgeClientErrorException();
        }
    }

    @Override
    public IbgeMunicipioResponse findMunicipio(String municipio) {
        try {
            IbgeMunicipioResponse ibgeMunicipio = ibgeClient.findMunicipio(municipio);

            if (Objects.isNull(ibgeMunicipio)) {
                throw new IbgeMunicipioNotFoundException(municipio, "o nome");
            }

            return ibgeMunicipio;
        } catch (FeignException fe) {
            fe.printStackTrace();
            throw new IbgeClientErrorException();
        }
    }
}
