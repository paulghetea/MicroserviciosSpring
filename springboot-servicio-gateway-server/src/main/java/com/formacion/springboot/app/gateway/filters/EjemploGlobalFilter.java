package com.formacion.springboot.app.gateway.filters;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class EjemploGlobalFilter implements GlobalFilter{
	
	private final Logger logger = LoggerFactory.getLogger(EjemploGlobalFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		//Exchange es la request
		logger.info("ejecutar comando pre");
		//Hasta aquí se ejecutan los filtros pre. Los "request" deben ir en el "pre"
		exchange.getRequest().mutate().headers(h-> h.add("token", "12345")); //añadimos un header al request
		//A partir de aquí se ejecutarán filtros post
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			logger.info("ejecutando filtro post");
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor->{
				exchange.getResponse().getHeaders().add("token", valor);
			});
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build()); //añadimos una cookie
			//exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN); //cambiamos la respuesta a texto
		}));
	}
}
