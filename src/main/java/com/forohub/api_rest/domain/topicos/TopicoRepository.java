package com.forohub.api_rest.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByEstadoFalse(Pageable paginacion);

    //Comprobar si el titulo del topico ya esta registrado
    Boolean existsByTituloIgnoreCase(String titulo);

    //Comprobar si el mensaje del topico ya esta registrado
    Boolean existsByMensajeIgnoreCase(String titulo);
}
