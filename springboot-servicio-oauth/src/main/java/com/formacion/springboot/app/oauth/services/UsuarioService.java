package com.formacion.springboot.app.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.formacion.springboot.app.commons.usuarios.models.entity.Usuario;
import com.formacion.springboot.app.oauth.clients.UsuarioFeignClient;

@Service
public class UsuarioService implements UserDetailsService { // Permite autenticar el usuario por el username

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUsername(username); //buscamos en usuarios a través del cliente feign el usuario por su username

		if (usuario == null) { //en caso de que no exista el nombre de usuario...
			log.error("Error en el login, no existe el usuario " + username + " en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login, no existe el usuario " + username + " en el sistema");
		}

		// Introducimos los Roles del usuario en una Lista de tipo Spring Secutiry (SimpleGrantedAuthority) que luego introduciremos en un usuario de tipo Spring Security
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre())) 
				.peek(authority -> log.info("Role: " + authority.getAuthority())) // Creamos mensaje de log con los roles que dispone el usuario
				.collect(Collectors.toList()); // Convertimos el stream de nuevo en una lista
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, //Creamos y retornamos un nuevo usuario de tipo Spring Security
				authorities); // authorities = roles

	}
}
