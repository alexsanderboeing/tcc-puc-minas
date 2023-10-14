package com.juntamedica.variavel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface VariavelRepository extends JpaRepository<Variavel, Long> {

    @Query("select v from Variavel v where v.id in :ids")
    List<Variavel> findAllLayout(List<Long> ids);
}
