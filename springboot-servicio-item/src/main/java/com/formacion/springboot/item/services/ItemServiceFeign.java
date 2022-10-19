package com.formacion.springboot.item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacion.springboot.item.clientes.ProductoClienteRest;
import com.formacion.springboot.item.entities.Item;

@Service("serviceFeign")
public class ItemServiceFeign implements ItemService {

	@Autowired
	ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {
		// TODO Auto-generated method stub
		return clienteFeign.listar().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		// TODO Auto-generated method stub
		 return new Item(clienteFeign.detalle(id), cantidad);
	}

}
