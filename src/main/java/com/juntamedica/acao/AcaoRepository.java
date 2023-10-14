package com.juntamedica.acao;

import com.juntamedica.acao.payload.AcaoComboResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public interface AcaoRepository extends JpaRepository<Acao, Long> {

    @Query("select a from Acao a "
            + "where (:nome is null or a.nome like (:nome%)) ")
    Page<Acao> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);

    @Query("select new com.juntamedica.acao.payload.AcaoComboResponse(a.id, a.nome) " +
            "from Acao a where (:nome is null or a.nome like (%:nome%)) ")
    Page<AcaoComboResponse> findAllCombo(String nome, Pageable pageable);

    @Query("select max(a.ordemEmTela) from Acao a")
    Long findLastScreenOrder();

    @Modifying
    @Transactional
    @Query("update Acao a " +
           "set a.ordemEmTela = a.ordemEmTela - 1 " +
           "where a.ordemEmTela > :acaoOrdemAtual")
    void updateOrdemEmTela(Long acaoOrdemAtual);

    Optional<Acao> findByOrdemEmTela(Long ordemEmTela);

    @Query("select a from Acao a where a.ordemEmTela = :ordemEmTela and a.id <> :id")
    Optional<Acao> findByOrdemEmTelaAndNotId(Long ordemEmTela, Long id);

}
