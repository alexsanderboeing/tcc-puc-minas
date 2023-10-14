package com.juntamedica.auditor;

import com.juntamedica.auditor.payload.AuditorComboResponse;
import com.juntamedica.auditor.payload.AuditorRestricaoDesempatadorResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AuditorRepository extends PagingAndSortingRepository<Auditor, Long> {

    @Query("select a from Auditor a where (:nome is null or a.nome like :nome%)")
    Page<Auditor> findByNome(String nome, Pageable pageable);

    @Query("select a from Auditor a where a.nome like %:term%"
            + " or (:auditorId is not null and :auditorId = a.id )"
            + " or exists (select 1 from AuditorConselho ac where ac.auditor.id = a.id and ac.numeroConselho like %:term%)"
            + " order by (case when (:auditorId is not null and :auditorId = a.id) then 1 else 2 end)")
    Page<Auditor> findAllByNomeOrConselhoNumeroOrAuditorId(String term, Long auditorId, Pageable pageable);

    @Query("select new com.juntamedica.auditor.payload.AuditorComboResponse(a.id, a.nome) from Auditor a where a.preCadastro = :preCadastro "
            + " and (a.nome like %:term%"
            + " or exists (select 1 from AuditorConselho ac where ac.auditor.id = a.id and ac.numeroConselho like %:term%))"
            + " order by a.nome")
    List<AuditorComboResponse> findAllByNomeOrConselhoNumeroAndPreCadastro(String term, Boolean preCadastro);

    @Query("select a from Auditor a "
            + "where (:nome is null or a.nome like (%:nome%)) "
            + "  and (:operadoraId is null or a.operadora.id = :operadoraId) "
            + "  and (:preCadastro is null or a.preCadastro = :preCadastro)")
    Page<Auditor> findAllGrid(String nome, Long operadoraId, Boolean preCadastro, Pageable pageable);

    @Query("select new com.juntamedica.auditor.payload.AuditorRestricaoDesempatadorResponse(a.id, a.nome) from Auditor a"
            + " where exists (select 1 from DesempatadorRestricao dr"
            + "    left join AuditorConselho ac on dr.auditor.id = ac.auditor.id"
            + "                     where dr.desempatador.id = :desempatadorId"
            + "                           and dr.auditor.id = a.id"
            + "                           and (dr.auditor is not null and"
            + "                           exists (select 1 from AuditorConselho ac_dr"
            + "                                   where ac_dr.auditor.id = a.id and"
            + "                                           ac_dr.numeroConselho = :crm and"
            + "                                           ac_dr.ufConselho = :uf)))")
    List<AuditorRestricaoDesempatadorResponse> findAllByRestricaoDesempatador(Long desempatadorId, String crm, String uf);
}
