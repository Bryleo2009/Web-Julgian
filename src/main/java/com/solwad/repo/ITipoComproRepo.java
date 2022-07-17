package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.TipoCompro;
@Repository
@Transactional
public interface ITipoComproRepo extends CrudRepository<TipoCompro, Integer>{

	@Modifying
	@Query(value="DELETE FROM tipo_compro", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table `solwad`.`tipo_compro` AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`tipo_compro`\r\n"
			+ "(`nombre_tc`)\r\n"
			+ "VALUES\r\n"
			+ "('Boleta');", nativeQuery = true)
	void tc1();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`tipo_compro`\r\n"
			+ "(`nombre_tc`)\r\n"
			+ "VALUES\r\n"
			+ "('Factura');", nativeQuery = true)
	void tc2();
}
