package com.juntamedica.beneficiario.factory;

import com.juntamedica.anexo.Anexo;
import com.juntamedica.anexo.factory.AnexoFactory;
import com.juntamedica.beneficiario.Beneficiario;
import com.juntamedica.beneficiario.payload.BeneficiarioRequest;
import com.juntamedica.beneficiario.payload.BeneficiarioResponse;
import com.juntamedica.contato.Contato;
import com.juntamedica.contato.factory.ContatoFactory;
import com.juntamedica.dominio.Dominio;
import com.juntamedica.endereco.Endereco;
import com.juntamedica.endereco.factory.EnderecoFactory;
import com.juntamedica.operadora.Operadora;
import com.juntamedica.operadora.factory.OperadoraFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BeneficiarioFactory {

    @Autowired
    private EnderecoFactory enderecoFactory;

    @Autowired
    private ContatoFactory contatoFactory;

    @Autowired
    private OperadoraFactory operadoraFactory;

    @Autowired
    private AnexoFactory anexoFactory;

    public Beneficiario build(BeneficiarioRequest beneficiarioRequest) {
        Beneficiario beneficiario = Beneficiario.builder()
                .id(beneficiarioRequest.getId())
                .operadora(
                        beneficiarioRequest.getOperadoraId() == null
                                ? null
                                : Operadora.builder()
                                    .id(beneficiarioRequest.getOperadoraId())
                                    .build())
                .preCadastro(
                        beneficiarioRequest.getPreCadastro() == null ? false : beneficiarioRequest.getPreCadastro())
                .codigo(beneficiarioRequest.getCodigo())
                .nome(beneficiarioRequest.getNome())
                .observacao(beneficiarioRequest.getObservacao())
                .dmSexoBeneficiario(
                        beneficiarioRequest.getDmSexoBeneficiarioId() == null
                                ? null
                                : Dominio.builder()
                                    .id(beneficiarioRequest.getDmSexoBeneficiarioId())
                                    .build())
                .dataNascimento(beneficiarioRequest.getDataNascimento())
                .build();

        beneficiario.setEndereco(
                beneficiarioRequest.getEndereco() == null
                        ? null
                        : enderecoFactory.build(beneficiario, beneficiarioRequest.getEndereco()));

        if (beneficiarioRequest.getContatoList() != null && !beneficiarioRequest.getContatoList().isEmpty()) {
            beneficiario.setContatoList(contatoFactory.buildList(beneficiario, beneficiarioRequest.getContatoList()));
        }

        if (beneficiarioRequest.getAnexoList() != null && !beneficiarioRequest.getAnexoList().isEmpty()) {
            beneficiario.setAnexoList(anexoFactory.buildList(beneficiarioRequest.getAnexoList()));
        }

        return beneficiario;
    }

    public Beneficiario updateValues(Beneficiario beneficiario, BeneficiarioRequest beneficiarioRequest) {
        beneficiario.setCodigo(beneficiarioRequest.getCodigo());
        beneficiario.setPreCadastro(
                beneficiarioRequest.getPreCadastro() == null ? false : beneficiarioRequest.getPreCadastro());
        beneficiario.setObservacao(beneficiarioRequest.getObservacao());
        beneficiario.setNome(beneficiarioRequest.getNome());
        beneficiario.setDmSexoBeneficiario(
                beneficiarioRequest.getDmSexoBeneficiarioId() == null
                        ? null
                        : Dominio.builder()
                            .id(beneficiarioRequest.getDmSexoBeneficiarioId())
                            .build());
        beneficiario.setDataNascimento(beneficiarioRequest.getDataNascimento());
        beneficiario.setOperadora(
                beneficiarioRequest.getOperadoraId() == null
                        ? null
                        : Operadora.builder()
                            .id(beneficiarioRequest.getOperadoraId())
                            .build());
        beneficiario.setEndereco(
                beneficiarioRequest.getEndereco() == null
                        ? null
                        : enderecoFactory.build(beneficiario, beneficiarioRequest.getEndereco()));

        if (beneficiarioRequest.getContatoList() != null && !beneficiarioRequest.getContatoList().isEmpty()) {
            beneficiario.mergeContatoList(contatoFactory.buildList(beneficiario, beneficiarioRequest.getContatoList()));
        } else {
            beneficiario.mergeContatoList(Collections.emptyList());
        }

        beneficiario.mergeAnexoList(anexoFactory.buildList(beneficiarioRequest.getAnexoList()));

        return beneficiario;
    }

    public List<BeneficiarioResponse> buildResponseList(List<Beneficiario> beneficiarioList, Boolean isCombo) {
        return beneficiarioList.stream().map(
                beneficiarioItem -> buildResponse(beneficiarioItem, isCombo)).collect(Collectors.toList());
    }

    public BeneficiarioResponse buildResponse(Beneficiario beneficiario, Boolean isCombo) {
        BeneficiarioResponse beneficiarioResponse = BeneficiarioResponse.builder()
                .id(beneficiario.getId())
                .codigo(beneficiario.getCodigo())
                .nome(beneficiario.getNome())
                .operadoraId(!Objects.isNull(beneficiario.getOperadora()) ? beneficiario.getOperadora().getId() : null)
                .operadora(!Objects.isNull(beneficiario.getOperadora()) ? operadoraFactory.buildComboResponse(beneficiario.getOperadora()) : null)
                .build();

        if (!isCombo) {
            Dominio dmSexoBeneficiario = beneficiario.getDmSexoBeneficiario();
            if (dmSexoBeneficiario != null) {
                beneficiarioResponse.setDmSexoBeneficiarioId(dmSexoBeneficiario.getId());
                beneficiarioResponse.setDmSexoBeneficiarioDescricao(dmSexoBeneficiario.getDescricao());
            }

            beneficiarioResponse.setObservacao(beneficiario.getObservacao());
            beneficiarioResponse.setDataNascimento(beneficiario.getDataNascimento());

            Endereco endereco = beneficiario.getEndereco();
            if (endereco != null) {
                beneficiarioResponse.setEndereco(enderecoFactory.buildResponse(endereco));
            }

            List<Contato> contatoList = beneficiario.getContatoList();
            if (contatoList != null) {
                beneficiarioResponse.setContatoList(contatoFactory.buildResponseList(contatoList));
            }

            List<Anexo> anexoList = beneficiario.getAnexoList();
            if(anexoList != null) {
                beneficiarioResponse.setAnexoList(anexoFactory.buildResponseList(anexoList));
            }
        }

        return beneficiarioResponse;
    }

    public List<BeneficiarioResponse> buildResponseListGrid(List<Beneficiario> beneficiarioList, Boolean isCombo) {
        return beneficiarioList.stream().map(
                beneficiarioItem -> buildResponse(beneficiarioItem, isCombo)).collect(Collectors.toList());
    }

    public Beneficiario updateValuesByInfoEdit(Beneficiario beneficiario, BeneficiarioRequest beneficiarioRequest) {
        if (beneficiarioRequest.getNovoCodigo() == null) {
            beneficiario.setCodigo(beneficiarioRequest.getCodigo() == null
                    ? beneficiario.getCodigo()
                    : beneficiarioRequest.getCodigo());
            beneficiario.setObservacao(beneficiarioRequest.getObservacao() == null
                    ? beneficiario.getObservacao()
                    : beneficiarioRequest.getObservacao());
            beneficiario.setNome(beneficiarioRequest.getNome() == null ? beneficiario.getNome() : beneficiarioRequest.getNome());
            beneficiario.setDmSexoBeneficiario(
                    beneficiarioRequest.getDmSexoBeneficiarioId() == null
                            ? beneficiario.getDmSexoBeneficiario()
                            : Dominio.builder()
                            .id(beneficiarioRequest.getDmSexoBeneficiarioId())
                            .build());
            beneficiario.setDataNascimento(beneficiarioRequest.getDataNascimento() == null
                    ? beneficiario.getDataNascimento()
                    : beneficiarioRequest.getDataNascimento());
            beneficiario.setEndereco(
                    beneficiarioRequest.getEndereco() == null
                            ? beneficiario.getEndereco()
                            : enderecoFactory.updateValuesByInfoEdit(beneficiario,
                            beneficiario.getEndereco(),
                            beneficiarioRequest.getEndereco()));

            if (beneficiarioRequest.getContatoList() != null) {
                beneficiario.mergeContatoList(contatoFactory.buildList(beneficiario, beneficiarioRequest.getContatoList()));
            }
        } else if (beneficiarioRequest.getNovoCodigo()) {
            beneficiario.setCodigo(beneficiarioRequest.getCodigo());
            beneficiario.setPreCadastro(
                    beneficiarioRequest.getPreCadastro() == null ? false : beneficiarioRequest.getPreCadastro());
            beneficiario.setObservacao(beneficiarioRequest.getObservacao());
            beneficiario.setNome(beneficiarioRequest.getNome());
            beneficiario.setDmSexoBeneficiario(
                    beneficiarioRequest.getDmSexoBeneficiarioId() == null
                            ? null
                            : Dominio.builder()
                            .id(beneficiarioRequest.getDmSexoBeneficiarioId())
                            .build());
            beneficiario.setDataNascimento(beneficiarioRequest.getDataNascimento());
            beneficiario.setOperadora(
                    beneficiarioRequest.getOperadoraId() == null
                            ? null
                            : Operadora.builder()
                            .id(beneficiarioRequest.getOperadoraId())
                            .build());
            beneficiario.setEndereco(
                    beneficiarioRequest.getEndereco() == null
                            ? null
                            : enderecoFactory.build(beneficiario, beneficiarioRequest.getEndereco()));

            if (beneficiarioRequest.getContatoList() != null) {
                beneficiario.mergeContatoList(contatoFactory.buildList(beneficiario, beneficiarioRequest.getContatoList()));
            } else {
                beneficiario.mergeContatoList(Collections.emptyList());
            }
        } else if (!beneficiarioRequest.getNovoCodigo()) {
            beneficiario.setCodigo(beneficiarioRequest.getCodigo());
            beneficiario.setPreCadastro(
                    beneficiarioRequest.getPreCadastro() == null ? false : beneficiarioRequest.getPreCadastro());
            beneficiario.setObservacao(beneficiarioRequest.getObservacao());
            beneficiario.setNome(beneficiarioRequest.getNome());
            beneficiario.setDmSexoBeneficiario(
                    beneficiarioRequest.getDmSexoBeneficiarioId() == null
                            ? null
                            : Dominio.builder()
                            .id(beneficiarioRequest.getDmSexoBeneficiarioId())
                            .build());
            beneficiario.setDataNascimento(beneficiarioRequest.getDataNascimento());
            beneficiario.setOperadora(
                    beneficiarioRequest.getOperadoraId() == null
                            ? null
                            : Operadora.builder()
                            .id(beneficiarioRequest.getOperadoraId())
                            .build());
            beneficiario.setEndereco(
                    beneficiarioRequest.getEndereco() == null
                            ? null
                            : enderecoFactory.build(beneficiario, beneficiarioRequest.getEndereco()));

            if (beneficiarioRequest.getEndereco().getCep() == null &&
                    beneficiarioRequest.getEndereco().getLogradouro() == null &&
                    beneficiarioRequest.getEndereco().getNumero() == null &&
                    beneficiarioRequest.getEndereco().getBairro() == null &&
                    beneficiarioRequest.getEndereco().getLocalidade() == null &&
                    beneficiarioRequest.getEndereco().getBairro() == null &&
                    beneficiarioRequest.getEndereco().getUf() == null) {
                beneficiario.setEndereco(null);
            } else {
                beneficiario.setEndereco(enderecoFactory.build(beneficiario, beneficiarioRequest.getEndereco()));
            }

            if (beneficiarioRequest.getContatoList() != null) {
                beneficiario.mergeContatoList(contatoFactory.buildList(beneficiario, beneficiarioRequest.getContatoList()));
            } else {
                beneficiario.mergeContatoList(Collections.emptyList());
            }
        }

        return beneficiario;
    }
}
