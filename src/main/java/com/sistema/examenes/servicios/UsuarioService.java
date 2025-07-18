package com.sistema.examenes.servicios;

import java.util.Optional;
import java.util.Set;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;

public interface UsuarioService {

    public Usuario saveUser(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;

    public Usuario getUser(String username);

    public Optional<Usuario> deleteUser(Long userId);
}