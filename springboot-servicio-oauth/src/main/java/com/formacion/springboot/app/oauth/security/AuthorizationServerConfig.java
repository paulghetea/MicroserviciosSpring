package com.formacion.springboot.app.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer // Establecer como clase servidor de autorización
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	// Inyección de dependencias de los beans creados en SpringSecurityConfig
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception { // Permisos que tendrán los endpoints del servidor de autorización para generar y validar el token.
		security.tokenKeyAccess("permitAll()") // Endpoint para generar el token. Cada vez que se registra un usuario con POST oauth/token, Valida credenciales y las autentica.
				.checkTokenAccess("isAuthenticated()"); //Valida el token si el cliente está autenticado
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // Registrar todo tipo de clientes front-end.
		clients.inMemory().withClient("frontendapp") //Registramos un cliente con el username
		.secret(passwordEncoder.encode("123456")) //contraseña del cliente encriptada
		.scopes("read", "write") //permisos del cliente
		.authorizedGrantTypes("password", "refresh_token") //Como se va a obtener el token. En este caso, formato usuario + contraseña. 
		.accessTokenValiditySeconds(3600) // tiempo máximo de validez del token
		.refreshTokenValiditySeconds(3600);  // tiempo antes de que el token se regenere
		
		//si quisiésemos nuevos clientes, añadimos .and()
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter());
	}

	@Bean
	private JwtTokenStore tokenStore() {
		// TODO Auto-generated method stub
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("aeiou_codigo_secreto"); // código clave para crear el token
		return tokenConverter;
	}

}
