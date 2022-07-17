package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.solwad.model.Usuario;
@Repository
@Transactional
public interface IUsuarioRepo extends CrudRepository<Usuario, Integer>{
	@Modifying
	@Query(value="DELETE FROM usuario", nativeQuery = true)
	void delete();
	@Modifying
	@Query(value="alter table `solwad`.`usuario` AUTO_INCREMENT=1;", nativeQuery = true)
	void reinicio();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`usuario`\r\n"
			+ "(`estado_user`,\r\n"
			+ "`password`,\r\n"
			+ "`username`,\r\n"
			+ "`dni_traba`,\r\n"
			+ "`id_rol`)\r\n"
			+ "VALUES\r\n"
			+ "(1,'$2a$10$Zmb81nI58LGVRk.CXYk5/.NXMp3gV3LCKTmZKJk3bBaqrrIzg3CEe','NULL','00000000',1);", nativeQuery = true)//12345
	void nulo();
	@Modifying
	@Query(value="INSERT INTO `solwad`.`usuario`\r\n"
			+ "(`estado_user`,\r\n"
			+ "`password`,\r\n"
			+ "`username`,\r\n"
			+ "`dni_traba`,\r\n"
			+ "`id_rol`)\r\n"
			+ "VALUES\r\n"
			+ "(1,'$2a$10$Zmb81nI58LGVRk.CXYk5/.NXMp3gV3LCKTmZKJk3bBaqrrIzg3CEe','admin','71850926',1);", nativeQuery = true)//12345
	void prueba();
	
	
	Usuario findByUsername(String username);
}
