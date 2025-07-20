package com.sistema.examenes.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sistema.examenes.repositorios.UsuarioRepositorio;
import com.sistema.examenes.servicios.impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// intercepta las invocaciones al servidor y comprueba el token, valida el token, comprueba si todo esta bien, autoriza la peticiones
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtils jwtUtils;

    JwtAuthenticationFilter(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                String requestTokenHeader = request.getHeader("Authorization");
                String username = null;
                String jwtToken = null;

                if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
                    jwtToken = requestTokenHeader.substring(7);

                    try {
                            // extraer el username del token
                            username = this.jwtUtils.extractUsername(jwtToken);
                    } catch (ExpiredJwtException ExpiredJwtException) {
                            System.out.println("El token ha expirado");
                    }catch(Exception exception){
                            exception.printStackTrace();
                    }
                } else {
                    System.out.println("El token es invalido tio");
                }

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(username);

                    // validar token
                    if(this.jwtUtils.validateToken(jwtToken, userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }else {
                        System.out.println("Token invalido");
                    }

                    
                }

                filterChain.doFilter(request, response);
    }

}
