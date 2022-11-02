package com.formacion.springboot.app.commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	@NonNull
	private String username;
	@Column(length = 60)
	@NonNull
	private String password;
	@NonNull
	private Boolean enabled;
	@NonNull
	private String nombre;
	@NonNull
	private String apellidos;

	@Column(unique = true, length = 60)
	@NonNull
	private String email;

	private static final long serialVersionUID = 1L;

	@ManyToMany(fetch = FetchType.LAZY) // En una consulta no trae todos los roles, solo si se usa el método get
	@JoinTable(name = "usuarios_to_roles",  //Tabla conjunta
	joinColumns = @JoinColumn(name = "usuario_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"), 
	uniqueConstraints = {@UniqueConstraint(columnNames = { "usuario_id", "role_id" }) }) //restricción obligando que sean únicos
	private List<Rol> roles;

}
