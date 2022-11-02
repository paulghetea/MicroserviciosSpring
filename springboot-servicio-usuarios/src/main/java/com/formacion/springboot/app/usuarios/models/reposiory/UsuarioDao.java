package com.formacion.springboot.app.usuarios.models.reposiory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.formacion.springboot.app.usuarios.models.entity.Usuario;

@RepositoryRestController(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> { // Permite ordenar + CRUD Repository integrado
																				
	public Usuario findByUsername(String username); // = select u form Usuario where u.username=?1
	
//	@Query("select u from Usuario u where u.username=?1")
//	public Usuario obtenerPorUsername(String username);  //Ambos son válidos
	 

}
