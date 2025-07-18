package com.sistema.examenes.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.examenes.entidades.Rol;
import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.servicios.UsuarioService;



@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public Usuario saveUser(@RequestBody Usuario usuario) throws Exception {
        Set<UsuarioRol> roles = new HashSet<>();
        Rol rol = new Rol();
        rol.setRolId(3L);
        rol.setNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
		usuarioRol.setRol(rol);
		usuarioRol.setUsuario(usuario);


        return usuarioService.saveUser(usuario, roles);

    }
    @GetMapping("/{username}")
    public Usuario getUser(@PathVariable("username") String username){
        return usuarioService.getUser(username);
    }

    @DeleteMapping("/{usuarioId}")
    public String deleteUser(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.deleteUser(usuarioId);

        return "Usuario eliminado con exito";
    }
    
}
