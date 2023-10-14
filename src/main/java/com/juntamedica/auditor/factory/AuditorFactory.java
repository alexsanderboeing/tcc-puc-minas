package com.juntamedica.auditor.factory;

import com.juntamedica.auditor.Auditor;
import com.juntamedica.auditor.payload.AuditorFromRestricaoRequest;
import com.juntamedica.auditor.payload.AuditorRequest;
import com.juntamedica.auditor.payload.AuditorResponse;
import com.juntamedica.auditorconselho.factory.AuditorConselhoFactory;
import com.juntamedica.auditorespecialidade.factory.AuditorEspecialidadeFactory;
import com.juntamedica.operadora.Operadora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuditorFactory {

    @Autowired
    private AuditorConselhoFactory auditorConselhoFactory;

    @Autowired
    private AuditorEspecialidadeFactory auditorEspecialidadeFactory;

    public AuditorResponse buildResponse(Auditor auditor) {
        AuditorResponse auditorResponse = AuditorResponse.builder()
                .id(auditor.getId())
                .operadoraId(Objects.nonNull(auditor.getOperadora())
                        ? auditor.getOperadora().getId()
                        : null)
                .operadoraNomeFantasia(Objects.nonNull(auditor.getOperadora())
                        ? auditor.getOperadora().getNomeFantasia()
                        : null)
                .preCadastro(Objects.isNull(auditor.getPreCadastro()) ? false : auditor.getPreCadastro())
                .nome(auditor.getNome())
                .observacao(auditor.getObservacao())
                .build();

        if (auditor.getEspecialidadeList() != null) {
            auditorResponse.setEspecialidadeList(auditorEspecialidadeFactory.buildResponseList(auditor.getEspecialidadeList()));
        }

        if (auditor.getConselhoList() != null) {
            auditorResponse.setConselhoList(auditorConselhoFactory.buildResponseList(auditor.getConselhoList()));
        }

        return auditorResponse;
    }

    public List<AuditorResponse> buildResponseList(List<Auditor> auditorList) {
        return auditorList.stream().map(this::buildResponse).collect(Collectors.toList());
    }

    public Auditor build(AuditorRequest auditorRequest) {
        Auditor auditor = Auditor.builder()
                .id(auditorRequest.getId())
                .nome(auditorRequest.getNome())
                .observacao(auditorRequest.getObservacao())
                .operadora(Objects.nonNull(auditorRequest.getOperadoraId())
                        ? Operadora.builder().id(auditorRequest.getOperadoraId()).build()
                        : null)
                .preCadastro(auditorRequest.getPreCadastro())
                .build();

        if (auditorRequest.getEspecialidadeList() != null) {
            auditor.setEspecialidadeList(auditorEspecialidadeFactory.buildList(auditor, auditorRequest.getEspecialidadeList()));
        }

        if (auditorRequest.getConselhoList() != null) {
            auditor.setConselhoList(auditorConselhoFactory.buildList(auditor, auditorRequest.getConselhoList()));
        }

        return auditor;
    }

    public Auditor build(AuditorFromRestricaoRequest auditorRequest) {
        Auditor auditor = Auditor.builder()
                .id(auditorRequest.getId())
                .nome(auditorRequest.getNome())
                .preCadastro(false)
                .build();

        if (auditorRequest.getConselhoList() != null) {
            auditor.setConselhoList(auditorConselhoFactory.buildListRestricao(auditor, auditorRequest.getConselhoList()));
        }

        return auditor;
    }

    public Auditor updateValuesByInfoEdit(Auditor auditor, AuditorRequest auditorRequest) {
        if (auditor == null) {
            auditor = new Auditor();
        }
        if (auditorRequest.getNovoConselho() == null) {
            auditor.setNome(
                    auditorRequest.getNome() == null
                            ? auditor.getNome()
                            : auditorRequest.getNome());
            auditor.setObservacao(
                    auditorRequest.getObservacao() == null
                            ? auditor.getObservacao()
                            : auditorRequest.getObservacao());

            if (auditorRequest.getEspecialidadeList() != null) {
                auditor.mergeEspecialidadeList(
                        auditorEspecialidadeFactory.buildList(
                                auditor, auditorRequest.getEspecialidadeList())
                );
            }
        } else if (auditorRequest.getNovoConselho()) {
            auditor.setNome(auditorRequest.getNome());
            auditor.setObservacao(auditorRequest.getObservacao());

            if (auditorRequest.getConselhoList() != null) {
                auditor.mergeConselhoList(
                        auditorConselhoFactory.buildList(
                                auditor, auditorRequest.getConselhoList()));
            }

            if (auditorRequest.getEspecialidadeList() != null) {
                auditor.mergeEspecialidadeList(
                        auditorEspecialidadeFactory.buildList(
                                auditor, auditorRequest.getEspecialidadeList())
                );
            } else {
                auditor.mergeEspecialidadeList(Collections.emptyList());
            }
        } else if (!auditorRequest.getNovoConselho()) {
            auditor.setNome(auditorRequest.getNome());
            auditor.setObservacao(auditorRequest.getObservacao());

            if (auditorRequest.getConselhoList() != null) {
                auditor.mergeConselhoList(
                        auditorConselhoFactory.buildList(
                                auditor, auditorRequest.getConselhoList()));
            } else {
                auditor.mergeConselhoList(Collections.emptyList());
            }

            if (auditorRequest.getEspecialidadeList() != null) {
                auditor.mergeEspecialidadeList(
                        auditorEspecialidadeFactory.buildList(
                                auditor, auditorRequest.getEspecialidadeList())
                );
            } else {
                auditor.mergeEspecialidadeList(Collections.emptyList());
            }
        }

        return auditor;
    }

    public Auditor updateValues(Auditor auditor, AuditorRequest auditorRequest) {
        auditor.setNome(auditorRequest.getNome());
        auditor.setObservacao(auditorRequest.getObservacao());
        auditor.setPreCadastro(auditorRequest.getPreCadastro() == null ? false : auditorRequest.getPreCadastro());
        auditor.setOperadora(
                Objects.nonNull(auditorRequest.getOperadoraId())
                        ? Operadora.builder().id(auditorRequest.getOperadoraId()).build()
                        : null
        );

        if (auditorRequest.getEspecialidadeList() != null) {
            auditor.mergeEspecialidadeList(
                    auditorEspecialidadeFactory.buildList(auditor, auditorRequest.getEspecialidadeList()));
        } else {
            auditor.mergeEspecialidadeList(Collections.emptyList());
        }

        if (auditorRequest.getConselhoList() != null) {
            auditor.mergeConselhoList(
                    auditorConselhoFactory.buildList(auditor, auditorRequest.getConselhoList()));
        } else {
            auditor.mergeConselhoList(Collections.emptyList());
        }

        return auditor;
    }
}
