package com.formacionbdi.springboot.app.item.models;

import lombok.Getter;
import lombok.Setter;

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
