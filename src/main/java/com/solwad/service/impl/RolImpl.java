package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Rol;
import com.solwad.repo.IRolRepo;
import com.solwad.service.IRolService;

@Service
public class RolImpl extends CRUDImpl<Rol, Integer> implements IRolService{

	@Autowired
	private IRolRepo repo;
	
	@Override
	protected CrudRepository<Rol, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	public void table_prueba() {
		repo.rol1();
		repo.rol2();
	}
	public void table_delete() {
		repo.deleten();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}

}
