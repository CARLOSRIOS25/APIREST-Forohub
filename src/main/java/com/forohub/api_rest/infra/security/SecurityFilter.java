package com.forohub.api_rest.infra.security;

import com.forohub.api_rest.domain.usuarios.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //obtener el token del header
        var authHeader = request.getHeader("Authorization");//.replace("Bearer ", "");    //Authorization es  el estandar
        if (authHeader != null){
            var token = authHeader.replace("Bearer ", ""); // obtenemos el token sin header "Bearer"
            var subject = tokenService.getSubject(token); // extraemos el usuario con el token
            if (subject != null)
            {
                //token valido
                var usuario = usuarioRepository.findByUsuario(subject);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null
                        ,usuario.getAuthorities()); // forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        //con esto el filtro responde llamando el siguiente filtro
        filterChain.doFilter(request, response);
    }

}
