package com.formacionbdi.springboot.app.item.models;

import lombok.Getter;
import lombok.Setter;
import com.formacion.springboot.app.commons.models.entity.Producto;

@Getter
@Setter
public class Item {

	private Producto producto;
	private Integer cantidad;

	public Item() {
	}

	public Item(Producto producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

}
