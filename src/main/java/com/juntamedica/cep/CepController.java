package com.juntamedica.cep;

import com.juntamedica.cep.payload.CepResponse;
import com.juntamedica.cep.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @GetMapping("{cep}")
    @ResponseStatus(HttpStatus.OK)
    public CepResponse findCep(@PathVariable Long cep) {
        return cepService.findCep(cep);
    }
}
