package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Trabajador;
import java.util.List;
@Repository
@Transactional
public interface ITrabajadorRepo extends CrudRepository<Trabajador, String>{

	@Modifying
	@Query(value="DELETE FROM trabajador", nativeQuery = true)
	void deleten();
	@Modifying
	@Query(value="alter table `solwad`.`trabajador` AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`trabajador`\r\n"
			+ "(`dni_traba`,\r\n"
			+ "`apellido_traba`,\r\n"
			+ "`direccion_traba`,\r\n"
			+ "`fecha_nac_traba`,\r\n"
			+ "`nombre_traba`,\r\n"
			+ "`telefono_traba`)\r\n"
			+ "VALUES\r\n"
			+ "('00000000','NULL',null,'2000-01-01','NULL',null);", nativeQuery = true)
	void nulo();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`trabajador`\r\n"
			+ "(`dni_traba`,\r\n"
			+ "`apellido_traba`,\r\n"
			+ "`direccion_traba`,\r\n"
			+ "`fecha_nac_traba`,\r\n"
			+ "`nombre_traba`,\r\n"
			+ "`telefono_traba`)\r\n"
			+ "VALUES\r\n"
			+ "('71850926','Morán Vega','Psj. Federico Villareal #147','2000-08-24','Bryan Andrés','969432568');", nativeQuery = true)
	void prueba();
        
    @Query(value = "SELECT * FROM trabajador T WHERE T.nombre_traba LIKE %?1% OR T.apellido_traba LIKE %?1%",
            nativeQuery = true)
    List<Trabajador> ReportTrabajador(String nombre);
}
