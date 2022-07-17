package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Nick;
import com.solwad.model.Rol;
import com.solwad.repo.INickRepo;
import com.solwad.repo.IRolRepo;
import com.solwad.service.INickService;
import com.solwad.service.IRolService;

@Service
public class NickImpl extends CRUDImpl<Nick, Integer> implements INickService{

	@Autowired
	private INickRepo repo;
	
	@Override
	protected CrudRepository<Nick, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	
	public void table_delete() {
		repo.deleten();
	}
	
	

}
