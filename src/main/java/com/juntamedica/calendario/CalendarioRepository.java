package com.juntamedica.calendario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CalendarioRepository extends PagingAndSortingRepository<Calendario, Long> {

    @Query("select c from Calendario c "
            + "where (:nome is null or c.nome like (%:nome%)) "
            + " and (:ativo is null or c.ativo = :ativo) ")
    Page<Calendario> findWithFilters(String nome, Boolean ativo, Pageable pageable);
}
