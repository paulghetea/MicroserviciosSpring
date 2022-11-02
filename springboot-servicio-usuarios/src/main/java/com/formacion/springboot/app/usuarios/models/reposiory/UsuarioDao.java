package com.formacion.springboot.app.usuarios.models.reposiory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import com.formacion.springboot.app.usuarios.models.entity.Usuario;

@RepositoryRestController(path="usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> { // Permite ordenar + CRUD Repository integrado

	
	public Usuario findByUsername(String username); // = select u form Usuario where u.username=?1
	//uri -> http://localhost:8090/api/usuarios/usuarios/search/findByUsername?username=maria
	
	//Si queremos persnoalizar la uri a buscar:
	//@RestResource(path="buscar-username")
	//public Usuario findByUsername(@Param("nombre") String username); -> uri = http://localhost:8090/api/usuarios/usuarios/search/buscar-username?nombre=maria
	
//	@Query("select u from Usuario u where u.username=?1")
//	public Usuario obtenerPorUsername(String username);  //Ambos son válidos
	 

}
