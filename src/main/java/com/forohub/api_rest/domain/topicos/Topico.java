package com.forohub.api_rest.domain.topicos;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Entity(name = "Topico")
@NoArgsConstructor@AllArgsConstructor
@Getter
@Table(name = "topicos")
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fecha_creacion;
    private Boolean estado;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datosTopico) {
        this.titulo = datosTopico.titulo();
        this.mensaje = datosTopico.mensaje();
        this.fecha_creacion = LocalDate.now();
        this.estado = false;
        this.autor = datosTopico.autor();
        this.curso = datosTopico.curso();
    }

    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico, Long id){
        if (id != null) {
            if (datosActualizarTopico.titulo() != null) {
                this.titulo = datosActualizarTopico.titulo();
            }
            if (datosActualizarTopico.mensaje() != null) {
                this.mensaje = datosActualizarTopico.mensaje();
            }
        }
    }

    public void resolverTopico(){
        this.estado = true;
    }
}
