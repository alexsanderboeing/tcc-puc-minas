package com.juntamedica.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findByUsername(String username);

    Optional<Usuario> findByUsernameAndExpiredPassword(String username, Boolean expiredPassword);

    List<Usuario> findByPasswordExpirationDate(Date passwordExpirationDate);

    List<Usuario> findAll();

    @Query("select u from Usuario u where u.id in (:ids)")
    List<Usuario> findAllByIds(List<Long> ids);

    @Query("select distinct u from Usuario u left join UsuarioPerfil up on up.usuario.id = u.id left join Perfil p on p.id = up.perfil.id "
            + " where (:active is null or u.active = :active)"
            + " and (:nome is null or u.name like (%:nome%) or u.username like (%:nome%) or u.email like (%:nome%))"
            + " and (:nomePerfil is null or p.type like (%:nomePerfil%)) ")
    Page<Usuario> findByFilter(String nome, Boolean active, String nomePerfil, Pageable pageable);
}
