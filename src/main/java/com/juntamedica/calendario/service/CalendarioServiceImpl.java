package com.juntamedica.calendario.service;

import com.juntamedica.calendario.Calendario;
import com.juntamedica.calendario.CalendarioRepository;
import com.juntamedica.calendario.exception.CalendarioNotFoundException;
import com.juntamedica.calendario.factory.CalendarioFactory;
import com.juntamedica.calendario.payload.CalendarioEditRequest;
import com.juntamedica.calendario.payload.CalendarioRequest;
import com.juntamedica.calendario.payload.CalendarioResponse;
import com.juntamedica.permissao.service.PermissaoService;
import com.juntamedica.utils.exception.DatabaseConstraintViolationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CalendarioServiceImpl implements CalendarioService {

    @Autowired
    private CalendarioRepository calendarioRepository;

    @Autowired
    private CalendarioFactory calendarioFactory;

    @Autowired
    private PermissaoService permissaoService;

    @Override
    public void delete(Long calendarioFeriadoId) {
        permissaoService.hasPermission("menu_configuracoes.menu_calendario:view");

        try {
            calendarioRepository.deleteById(calendarioFeriadoId);
        } catch (DataIntegrityViolationException e) {
            String constraintName = ((ConstraintViolationException) e.getCause())
                    .getConstraintName().replaceAll(".*\\.", "");

            String tableFK = "";

            switch (constraintName) {
                case "STATUS_ETAPA_CALENDARIO_FK":
                    tableFK = "status etapa";
                    break;
                case "CONTRATO_FLUXO_CALENDARIO_FK":
                    tableFK = "contrato fluxo processo";
                    break;
                default:
                    tableFK = "tabelas";
                    break;
            }

            String tableName = "calendário";
            throw new DatabaseConstraintViolationException(
                    "Existem " + tableFK + " relacionados a este " + tableName + ", não foi possível excluir!");

        }
    }

    @Override
    public CalendarioResponse save(CalendarioRequest calendarioRequest) {
        permissaoService.hasPermission("menu_configuracoes.menu_calendario:view");

        Calendario calendario = calendarioRepository.save(calendarioFactory.build(calendarioRequest));

        return calendarioFactory.buildResponse(calendario);
    }

    @Override
    public CalendarioResponse edit(CalendarioEditRequest calendarioEditRequest) {
        permissaoService.hasPermission("menu_configuracoes.menu_calendario:view");

        Calendario calendario = calendarioRepository.findById(calendarioEditRequest.getId())
                .orElseThrow(() -> new CalendarioNotFoundException(calendarioEditRequest.getId()));

        calendario = calendarioFactory.updateValues(calendario, calendarioEditRequest);
        calendario = calendarioRepository.save(calendario);

        return calendarioFactory.buildResponse(calendario);
    }

    @Override
    public CalendarioResponse find(Long id) {
        Calendario calendario = calendarioRepository.findById(id)
                .orElseThrow(() -> new CalendarioNotFoundException(id));

        return calendarioFactory.buildResponse(calendario);
    }

    @Override
    public Calendario findEntity(Long id) {
        return calendarioRepository.findById(id).orElseThrow(() -> new CalendarioNotFoundException(id));
    }

    @Override
    public Page<CalendarioResponse> findAll(CalendarioRequest calendarioRequest, Pageable pageable) {
        permissaoService.hasPermission("menu_configuracoes.menu_calendario:view");

        String nome = null;
        Boolean ativo = null;

        if (calendarioRequest != null) {
            nome = calendarioRequest.getNome();
            ativo = calendarioRequest.getAtivo();
        }

        Page<Calendario> calendarioPage = calendarioRepository.findWithFilters(nome, ativo, pageable);

        return new PageImpl<>(calendarioFactory
                .buildResponseList(calendarioPage.getContent()), pageable, calendarioPage.getTotalElements());
    }
}
