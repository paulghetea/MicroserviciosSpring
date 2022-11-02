package com.formacionbdi.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			// producto.setPort(port);
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		// producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
//		try {
//			Thread.sleep(2000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED) // Respuesta esperada
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping("/editar")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void editar(@PathVariable Long id) {
		productoService.delete(id);
	}

}
