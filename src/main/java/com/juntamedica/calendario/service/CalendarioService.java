package com.juntamedica.calendario.service;

import com.juntamedica.calendario.Calendario;
import com.juntamedica.calendario.payload.CalendarioEditRequest;
import com.juntamedica.calendario.payload.CalendarioRequest;
import com.juntamedica.calendario.payload.CalendarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CalendarioService {

    void delete(Long calendarioId);

    CalendarioResponse save(CalendarioRequest calendarioRequest);

    CalendarioResponse edit(CalendarioEditRequest calendarioEditRequest);

    CalendarioResponse find(Long id);

    Calendario findEntity(Long id);

    Page<CalendarioResponse> findAll(CalendarioRequest calendarioRequest, Pageable pageable);
}
