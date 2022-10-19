package com.formacion.springboot.app.productos.repository;

import org.springframework.data.repository.CrudRepository;

import com.formacion.springboot.app.productos.entities.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
