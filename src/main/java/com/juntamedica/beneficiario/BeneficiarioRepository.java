package com.juntamedica.beneficiario;

import com.juntamedica.beneficiario.payload.BeneficiarioComboResponse;
import com.juntamedica.beneficiario.payload.BeneficiarioGridResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Optional;

public interface BeneficiarioRepository extends PagingAndSortingRepository<Beneficiario, Long> {

    @Query("select b from Beneficiario b where b.id = (" +
            "select max(be.id) from Beneficiario be" +
            " where be.codigo like :codigo" +
            "   and be.operadora.id = :operadoraId" +
            "   and nvl(be.preCadastro, 0) = :preCadastro)")
    Beneficiario findBeneficiarioByCodigoAndOperadora(String codigo, Long operadoraId, Boolean preCadastro);

    @Query("select B from Beneficiario B "
            + "where (:nome is null or B.nome like (:nome%)) "
            + " and (:operadoraId is null or B.operadora.id = :operadoraId) ")
    Page<Beneficiario> findWithFilters(String nome, Long operadoraId, Pageable pageable);

    @Query("select new com.juntamedica.beneficiario.payload.BeneficiarioComboResponse(max(B.id), B.codigo, B.nome)"
            + " from Beneficiario B "
            + "where (:searchTerm is null or B.nome like (%:searchTerm%) or B.codigo like (%:searchTerm%)) "
            + " and (:operadoraId is null or B.operadora.id = :operadoraId) "
            + "group by B.codigo, B.nome ")
    Page<BeneficiarioComboResponse> findWithFiltersCombo(String searchTerm, Long operadoraId, Pageable pageable);

    @Query("select new com.juntamedica.beneficiario.payload.BeneficiarioComboResponse(max(B.id), B.codigo, B.nome) "
            + "from Beneficiario B "
            + "where (:searchTerm is null or B.nome like (%:searchTerm%) or B.codigo like (%:searchTerm%)) "
            + "and (:operadoraId is null or B.operadora.id = :operadoraId) "
            + "and B.preCadastro = 1 "
            + "group by B.codigo, B.nome")
    Page<BeneficiarioComboResponse> findWithFiltersComboPreCadastro(String searchTerm, Long operadoraId, Pageable pageable);

    @Query("select new com.juntamedica.beneficiario.payload.BeneficiarioGridResponse(max(B.id), B.codigo, B.nome, B.operadora.id, o.nomeFantasia as operadora) "
            + "from Beneficiario B "
            + "inner join Operadora o on o.id = B.operadora.id "
            + "where (:nome is null or B.nome like (:nome)) "
            + "and (:codigo is null or B.codigo = :codigo) "
            + "and (:operadoraId is null or B.operadora.id = :operadoraId) "
            + "and B.preCadastro = 1 "
            + "group by B.codigo, B.nome, B.operadora.id, o.nomeFantasia ")
    Page<BeneficiarioGridResponse> findWithFiltersGridPreCadastro(String nome, String codigo, Long operadoraId, Pageable pageable);

    Optional<Beneficiario> findByIdAndPreCadastro(Long beneficiarioId, Boolean preCadastro);
}
