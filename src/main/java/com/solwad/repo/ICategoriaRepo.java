package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Categoria;
@Transactional
@Repository
public interface ICategoriaRepo extends CrudRepository<Categoria, Integer>{
	@Modifying
	@Query(value="DELETE FROM categoria_product", nativeQuery = true)
	void deletecategoria();
	
	@Modifying
	@Query(value="INSERT INTO categoria_product(nombre_categ)\r\n"
			+ "VALUES ('NULL');", nativeQuery = true)
	void createcategoria();
	
	@Modifying
	@Query(value="alter table categoria_product AUTO_INCREMENT=1;", nativeQuery = true)
	void reiniciocategoria();
	
	@Modifying
	@Query(value="INSERT INTO categoria_product\r\n"
			+ "(nombre_categ)\r\n"
			+ "VALUES ('Joggers');", nativeQuery = true)
	void prueba();
}
