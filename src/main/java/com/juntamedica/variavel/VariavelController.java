package com.juntamedica.variavel;

import com.juntamedica.variavel.payload.VariavelResponse;
import com.juntamedica.variavel.service.VariavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("variavel")
public class VariavelController {

    @Autowired
    private VariavelService variavelService;

    @GetMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<VariavelResponse> findAll() {
        return variavelService.findAll();
    }

    @GetMapping("findAllLayout")
    @ResponseStatus(HttpStatus.OK)
    public List<VariavelResponse> findAllLayout() {
        return variavelService.findAllLayout();
    }
}
