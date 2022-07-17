package com.solwad.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.solwad.model.Nick;
import com.solwad.model.Usuario;
@Transactional
@Repository
public interface INickRepo extends CrudRepository<Nick, Integer>{
	@Modifying
	@Query(value="DELETE FROM temp", nativeQuery = true)
	void deleten();
	
	

	
}
