package com.formacion.springboot.item.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Item {
	@NonNull
	private Producto producto;
	@NonNull
	private Integer cantidad;
	
	public Double getTotal() {
		return producto.getPrecio()*cantidad.doubleValue();
	}
}
