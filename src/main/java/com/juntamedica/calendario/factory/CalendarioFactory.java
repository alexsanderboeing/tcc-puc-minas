package com.juntamedica.calendario.factory;

import com.juntamedica.calendario.Calendario;
import com.juntamedica.calendario.payload.CalendarioEditRequest;
import com.juntamedica.calendario.payload.CalendarioRequest;
import com.juntamedica.calendario.payload.CalendarioResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalendarioFactory {

    public Calendario build(CalendarioRequest calendarioRequest) {
        return Calendario.builder()
                .id(calendarioRequest.getId())
                .nome(calendarioRequest.getNome())
                .ativo(calendarioRequest.getAtivo())
                .build();
    }

    public List<Calendario> buildList(List<CalendarioRequest> calendarioRequestList) {
        return calendarioRequestList.stream().map(this::build).collect(Collectors.toList());
    }

    public CalendarioResponse buildResponse(Calendario calendario) {
        return CalendarioResponse.builder()
                .id(calendario.getId())
                .nome(calendario.getNome())
                .ativo(calendario.getAtivo())
                .build();
    }

    public List<CalendarioResponse> buildResponseList(List<Calendario> calendarioList) {
        return calendarioList.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public Calendario updateValues(Calendario calendario, CalendarioEditRequest calendarioEditRequest) {
        calendario.setNome(calendarioEditRequest.getNome());
        calendario.setAtivo(calendarioEditRequest.getAtivo());

        return  calendario;
    }
}
