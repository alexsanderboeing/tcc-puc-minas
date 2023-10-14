package com.juntamedica.anexo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface AnexoRepository extends JpaRepository<Anexo, Long> {

	Optional<Anexo> findTop1ByUuid(String uuid);

	List<Anexo> findByClassificacaoAnexoId(Long classificacaoAnexoId);

	@Query("select a from Anexo a  " +
			"left join ClassificacaoAnexo ca on ca.id = a.classificacaoAnexo.id " +
			"where (:visivelTelaAnexo is null or COALESCE(ca.visivelTelaAnexo, true) = :visivelTelaAnexo) " +
			"and a.id in (select al.id from Processo p join p.anexoList al where p.id = :processoId) ")
	Page<Anexo> findByProcessoId(Long processoId, Boolean visivelTelaAnexo, Pageable pageable);

	@Query("select a from Anexo a where a.id in (:anexosIds)")
	List<Anexo> findByIds(List<Long> anexosIds);
}
