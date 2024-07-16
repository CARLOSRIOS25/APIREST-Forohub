package com.forohub.api_rest.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;


public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso ) {
}
