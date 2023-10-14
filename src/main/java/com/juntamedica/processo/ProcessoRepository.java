package com.juntamedica.processo;

import com.juntamedica.processo.payload.ProcessoTotalizadorMenuResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    @Query("select count(p) from Processo p where p.fluxoProcesso.id = :fluxoProcessoId group by p.fluxoProcesso.id")
    Integer findByFluxoProcessoId(Long fluxoProcessoId);

    @Query("select p from Processo p where COALESCE(p.modoRascunho, true) = false and p.id = :processoId and (" +
            ":userOperadoraId is null or p.operadoraSolicitante.id = :userOperadoraId)")
    Processo findByUserOperadoraPermission(Long processoId, Long userOperadoraId);

    @Query("select p.administrativoFesc.id from Processo p where p.id = " +
            "(select max(pp.id) from Processo pp inner join pp.administrativoFesc af where pp.operadoraSolicitante.id = :operadoraSolicitanteId)")
    Long findUltimoAdministrativoFescExtIdByOperadoraSolicitanteId(Long operadoraSolicitanteId);

    @Query("select p.auditorQualidade.id from Processo p where p.id = " +
            "(select max(pp.id) from Processo pp inner join pp.auditorQualidade aq where pp.operadoraSolicitante.id = :operadoraSolicitanteId)")
    Long findUltimoAuditorQualidadeIdByOperadoraSolicitanteId(Long operadoraSolicitanteId);

    @Query(value = "SELECT new com.juntamedica.processo.payload.ProcessoTotalizadorMenuResponse(" +
            "(SELECT count(*) " +
            "FROM Processo p " +
            "INNER JOIN ProcessoHistoricoNotificacao phn ON phn.processo.id = p.id " +
            "WHERE p.id = :processoId) AS totalNotificacao, " +
            "(SELECT count(*) " +
            "FROM Processo p " +
            "INNER JOIN ChatMensagem cm ON cm.processo.id = p.id " +
            "WHERE p.id = :processoId " +
            "AND NOT EXISTS (SELECT cml FROM ChatMensagemLida cml WHERE cml.chatMensagem = cm.id and cml.usuarioExtId = :usuarioId) " +
            "AND cm.papelDestino.id = :userPapelId " +
            "AND (:usuarioDesempatadorId IS NULL OR (cm.createdBy = :usuarioDesempatadorId OR cm.usuarioDestinoId = :usuarioDesempatadorId))" +
            ") AS totalMensagem, " +
            "(SELECT count(*) " +
            "FROM Processo p " +
            "INNER JOIN ProcessoAnexo pa ON pa.processo.id = p.id " +
            "iNNER JOIN Anexo a on a.id = pa.anexo.id " +
            "LEFT JOIN ClassificacaoAnexo ca on ca.id = a.classificacaoAnexo.id " +
            "WHERE p.id = :processoId and ca.visivelTelaAnexo = true) AS totalAnexo)  " +
            "FROM Processo p WHERE p.id = :processoId")
    ProcessoTotalizadorMenuResponse findTotalizadorMenu(Long processoId, Long userPapelId, Long usuarioDesempatadorId, Long usuarioId);
}
