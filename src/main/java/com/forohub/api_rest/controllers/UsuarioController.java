package com.forohub.api_rest.controllers;

import com.forohub.api_rest.domain.usuarios.DatosAutenticacionUsuario;
import com.forohub.api_rest.domain.usuarios.Usuario;
import com.forohub.api_rest.infra.security.DatosJWTToken;
import com.forohub.api_rest.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    //con esta clase iniciamos el procseso de autenticacion
    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping
//    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacioUsuario){
//        //con DatosAutenticacionUsuario creamos el token para la autorizacion
//        Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacioUsuario.usuario()
//                ,datosAutenticacioUsuario.clave());
//        authenticationManager.authenticate(token);
//        return ResponseEntity.ok().build();
//    }

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacioUsuario){
        //con DatosAutenticacionUsuario creamos el token para la autorizacion
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacioUsuario.usuario()
                ,datosAutenticacioUsuario.clave());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

}
