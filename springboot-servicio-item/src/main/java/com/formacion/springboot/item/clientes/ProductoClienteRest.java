package com.formacion.springboot.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacion.springboot.item.entities.Producto;

//Cliente Feign. Nombre y properties es el establecido en el servicio al que se intenta acceder dentro del properties
@FeignClient(name="servicio-productos", url="localhost:8001")
public interface ProductoClienteRest {
	
	//nombre de enpoint igual al del controlador del servidor al que se quiere acceder.
	@GetMapping("/listar")
	public List<Producto> listar();

	@GetMapping("/listar/{id}")
	public Producto detalle(@PathVariable Long id);
}
