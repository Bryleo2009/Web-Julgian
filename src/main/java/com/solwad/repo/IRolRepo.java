package com.solwad.repo;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Rol;
@Repository
@Transactional
public interface IRolRepo extends CrudRepository<Rol, Integer>{

	@Modifying
	@Query(value="DELETE FROM rol", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table rol AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO rol\r\n"
			+ "(nombre_tp)\r\n"
			+ "VALUES\r\n"
			+ "('ROLE_GERENTE');", nativeQuery = true)
	void rol1();
	@Modifying
	@Query(value="INSERT INTO rol\r\n"
			+ "(nombre_tp)\r\n"
			+ "VALUES\r\n"
			+ "('ROLE_EMPLEADO');", nativeQuery = true)
	void rol2();
}
