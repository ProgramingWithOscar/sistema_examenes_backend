package com.sistema.examenes.servicios.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepositorio;
import com.sistema.examenes.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Usuario saveUser(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario localUser = usuarioRepositorio.findByUsername(usuario.getUsername());

        if(localUser != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya existe");
        }else {
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }

            usuario.getUsuarioRoles().addAll(usuarioRoles);
            localUser = usuarioRepositorio.save(usuario);
        }

        return localUser;

    }

    @Override
    public Usuario getUser(String username) {
        return  usuarioRepositorio.findByUsername(username);
    }

    @Override
    public Optional<Usuario> deleteUser(Long userId) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(userId);

        if (usuario.isPresent()) {
                usuarioRepositorio.deleteById(userId);
            }

        return usuario;
    }


}
