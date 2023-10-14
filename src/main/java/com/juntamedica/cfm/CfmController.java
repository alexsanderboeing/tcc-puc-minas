package com.juntamedica.cfm;

import com.juntamedica.cfm.payload.CfmResponse;
import com.juntamedica.cfm.service.CfmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cfm")
public class CfmController {

    @Autowired
    private CfmService cfmService;

    @GetMapping("{crm}/{uf}")
    @ResponseStatus(HttpStatus.OK)
    public CfmResponse find(@PathVariable Integer crm, @PathVariable String uf) {
        return cfmService.findCfmByConselhoNumeroAndConselhoUf(crm, uf);
    }
}
