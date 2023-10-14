package com.juntamedica.cep;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CepRepository extends JpaRepository<Cep, Long> {

    Cep findByCodigo(Long cep);
}
