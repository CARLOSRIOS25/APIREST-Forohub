package com.forohub.api_rest.domain.topicos;

import java.time.LocalDate;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDate fecha_creacion,
        Boolean estado,
        String autor,
        String curso) {
}
