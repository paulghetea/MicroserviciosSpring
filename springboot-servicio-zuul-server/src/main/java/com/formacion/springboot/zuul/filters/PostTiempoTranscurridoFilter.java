package com.formacion.springboot.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//Calcula el tiempo de inicio del tipo "PRE" antes de que se resuelva la ruta
@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
	
	//Creamos un log para ver datos del gateway
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		//true ejecuta el método run (el filtro)
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//Lógica
		//Cogemos la request
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		//Creamos el log
		log.info(String.format("%s request enrutado a %s", request.getMethod(),request.getRequestURL().toString()));
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en miliseg %s", tiempoTranscurrido.doubleValue()));
		
		return null;
	}

	@Override
	public String filterType() {
		//se indica el tipo de filtro : pre, post, route
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}
	
}
