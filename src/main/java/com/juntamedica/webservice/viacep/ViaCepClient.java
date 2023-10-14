package com.juntamedica.webservice.viacep;

import com.juntamedica.webservice.viacep.payload.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCep", url = "${viacep.url}")
public interface ViaCepClient {

    @GetMapping("/{cep}/json")
    ViaCepResponse findCep(@PathVariable Long cep);
}
