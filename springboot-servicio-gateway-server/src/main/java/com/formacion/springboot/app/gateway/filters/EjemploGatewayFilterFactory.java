package com.formacion.springboot.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

//Permite crear un filtro para una request personalizada
//Es necesario que el Sufijo GatewayFilterFactory exista siempre en el nombre de la clase.
//El resto de la configuración se realiza en el properties, eligiendo el servicio al que se le va aplicar
@Component
public class EjemploGatewayFilterFactory
		extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {

	private final Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);

	public EjemploGatewayFilterFactory() {
		super(Configuracion.class);
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		return new OrderedGatewayFilter((exchange, chain) -> {
			logger.info("ejecutando pre gateway filter factory " + config.mensaje);
			// encima de chain filter -> pre
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				// a continuación -> post
				Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, cookie).build());
				});
				logger.info("ejecutando post gateway filter factory " + config.mensaje);
			}));

		}, 2);
	}

	
	
	//Pasarle el nombre a la clase con el que se recogerá en el properties
//	@Override
//	public String name() {
//		// TODO Auto-generated method stub
//		return "EjemploCookie";
//	}

	// Es necesario pasar a la clase abstracta una clase de configuración
	@Getter
	@Setter
	public static class Configuracion {
		// Agregamos cookie personalizada
		private String mensaje;
		private String cookieValor;
		private String cookieNombre;
	}

}
