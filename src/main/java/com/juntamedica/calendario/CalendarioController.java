package com.juntamedica.calendario;

import com.juntamedica.calendario.payload.CalendarioEditRequest;
import com.juntamedica.calendario.payload.CalendarioRequest;
import com.juntamedica.calendario.payload.CalendarioResponse;
import com.juntamedica.calendario.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("calendario")
public class CalendarioController {

    @Autowired
    private CalendarioService calendarioService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CalendarioResponse find(@PathVariable Long id) {
        return calendarioService.find(id);
    }

    @PostMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public Page<CalendarioResponse> findAll(@RequestBody(required = false) CalendarioRequest calendarioRequest,
                                            @PageableDefault(sort = {"nome"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return calendarioService.findAll(calendarioRequest, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CalendarioResponse save(@Valid @RequestBody CalendarioRequest calendarioRequest) {
        return calendarioService.save(calendarioRequest);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public CalendarioResponse edit(@Valid @RequestBody CalendarioEditRequest calendarioEditRequest) {
        return calendarioService.edit(calendarioEditRequest);
    }

    @DeleteMapping("{calendarioId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long calendarioId) {
        calendarioService.delete(calendarioId);
    }
}
