package com.forohub.api_rest.controllers;

import com.forohub.api_rest.domain.topicos.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico
            , UriComponentsBuilder uriComponentsBuilder){

        Boolean tituloUnico = topicoRepository.existsByTituloIgnoreCase(datosRegistroTopico.titulo());
        Boolean mensajeUnico = topicoRepository.existsByMensajeIgnoreCase(datosRegistroTopico.mensaje());

        if (!tituloUnico && !mensajeUnico){
            Topico t = topicoRepository.save(new Topico(datosRegistroTopico));

            DatosRespuestaTopico respuestaTopico = new DatosRespuestaTopico(t.getId(), t.getTitulo(),
                    t.getMensaje(), t.getFecha_creacion(), t.getEstado(), t.getAutor(), t.getCurso());

            //devuelve un header en la respuesta de la request, que nos sirve para buscar nuestro registro creado
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(t.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaTopico);
        }else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> ListaTopicos(@PageableDefault(size = 10, sort = "id") Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion)
                .map(t -> new DatosListaTopico(t.getId(), t.getTitulo(),
                        t.getMensaje(), t.getFecha_creacion(), t.getEstado(), t.getAutor(), t.getCurso())));
    }


    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoPorId(@PathVariable Long id){
        Topico t = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(t.getId(), t.getTitulo(),
                t.getMensaje(), t.getFecha_creacion(), t.getEstado(), t.getAutor(), t.getCurso());
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id){
        Topico t = topicoRepository.getReferenceById(id);

        //llamamos al metodo de la clase topico que mantiene los datos que no se cambian y los devuelve igual
        t.actualizarTopico(datosActualizarTopico, id);

        return ResponseEntity.ok(new DatosRespuestaTopico(t.getId(), t.getTitulo(),
                t.getMensaje(), t.getFecha_creacion(), t.getEstado(), t.getAutor(), t.getCurso()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Optional<Topico>> eliminarTopico(@PathVariable Long id){
        Optional<Topico> t = topicoRepository.findById(id);

        if (t.isPresent()){
            topicoRepository.deleteById(id);
        }
        return ResponseEntity.ok(t);
    }

}
