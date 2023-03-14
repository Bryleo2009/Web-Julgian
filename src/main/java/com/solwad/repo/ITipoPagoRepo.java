package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.TipoPago;
@Repository
@Transactional
public interface ITipoPagoRepo extends CrudRepository<TipoPago, Integer>{
	@Modifying
	@Query(value="DELETE FROM tipo_pago", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table tipo_pago AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO tipo_pago\r\n"
			+ "(nombre_tp)\r\n"
			+ "VALUES\r\n"
			+ "('Efectivo');", nativeQuery = true)
	void tp1();
	@Modifying
	@Query(value="INSERT INTO tipo_pago\r\n"
			+ "(nombre_tp)\r\n"
			+ "VALUES\r\n"
			+ "('Tarjeta');", nativeQuery = true)
	void tp2();
}
