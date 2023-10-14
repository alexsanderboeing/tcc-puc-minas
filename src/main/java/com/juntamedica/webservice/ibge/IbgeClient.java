package com.juntamedica.webservice.ibge;

import com.juntamedica.webservice.ibge.payload.IbgeEstadoResponse;
import com.juntamedica.webservice.ibge.payload.IbgeMunicipioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "ibge", url = "${ibge.url}")
public interface IbgeClient {

    @GetMapping("estados")
    List<IbgeEstadoResponse> findAllEstados();

    @GetMapping("estados/{uf}")
    IbgeEstadoResponse findEstado(@PathVariable String uf);

    @GetMapping("municipios")
    List<IbgeMunicipioResponse> findAllMunicipios();

    @GetMapping("estados/{uf}/municipios")
    List<IbgeMunicipioResponse> findAllMunicipiosPorEstado(@PathVariable String uf);

    @GetMapping("municipios/{municipio}")
    IbgeMunicipioResponse findMunicipio(@PathVariable String municipio);
}
