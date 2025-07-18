package com.sistema.examenes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sistema.examenes.servicios.UsuarioService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private UsuarioService usuarioService;
		
	public static void main(String[] args) {
		

		
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Usuario usuario = new Usuario();

		// usuario.setNombre("Oscar");
		// usuario.setApellido("Poveda");
		// usuario.setUsername("Poveda");
		// usuario.setPassword("12345");
		// usuario.setEmail("oscar@gmail.com");
		// usuario.setTelefono("12345");
		// usuario.setPerfil("oscar2025");

		// Rol rol = new Rol();
		// rol.setRolId(1L);
		// rol.setNombre("ADMIN");

		// Set<UsuarioRol> usuarioRoles = new HashSet<>();
		// UsuarioRol usuarioRol = new UsuarioRol();
		// usuarioRol.setRol(rol);
		// usuarioRol.setUsuario(usuario);
		// usuarioRoles.add(usuarioRol);


		// Usuario usuarioSaved = usuarioService.saveUser(usuario, usuarioRoles);

		// System.out.println(usuarioSaved);

	}

}
