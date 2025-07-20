package com.sistema.examenes.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.repositorios.UsuarioRepositorio;

// cargar un usuario con su username, buscar un usuario
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // inyectamos 
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepositorio.findByUsername(username);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return usuario;
    }

}
