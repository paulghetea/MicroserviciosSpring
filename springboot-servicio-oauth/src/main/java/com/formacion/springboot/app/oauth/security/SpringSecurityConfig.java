package com.formacion.springboot.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService usuarioService; // Inyecta el componente en concreto que ha sido implementado en la clase
												// UsuarioService. Busca un service que tenga anotacion @Service

	@Bean // Objecto singleton de Spring. A diferencia de component, service, etc. permite guardar una implementación propia.
	public static BCryptPasswordEncoder passwordEncoder() { // función que permite encriptar constraseña
		return new BCryptPasswordEncoder();
	}
	

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	// Los métodos anteriores son beans que serán usados en la configuración del servidor de configuración.

	@Override
	@Autowired // Para poder inyectar el método
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(this.usuarioService).passwordEncoder(passwordEncoder()); // Encripta contraseña con
																							// Bcrypt
	}

}
