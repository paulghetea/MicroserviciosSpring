package com.formacionbdi.springboot.app.item;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class AppConfig {

	// Para crear loadBalancer
	@Bean("clienteRest")
	@LoadBalanced
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}

	// Para personalizar parámetros por defecto del circuit breaker
	@Bean
	public Customizer<Resilience4JCircuitBreakerFactory> defaultCoCustomizer(){
		return factory -> factory.configureDefault(id -> {
			return new Resilience4JConfigBuilder(id)
					.circuitBreakerConfig(CircuitBreakerConfig.custom()
						.slidingWindowSize(10) //Ventana de prueba
						.failureRateThreshold(50) //Porcentaje de fallo dentro de la ventana de prueba
						.waitDurationInOpenState(Duration.ofSeconds(10L)) //Tiempo de espera en estado abierto
						.permittedNumberOfCallsInHalfOpenState(5) //máximo de número de llamadas en estado semi-abierto
						.build())
					.timeLimiterConfig(TimeLimiterConfig.ofDefaults()) //Tiempo máximo de espera para cargar. ofDefaults = por defecto
					.build();
		});
	}
}
