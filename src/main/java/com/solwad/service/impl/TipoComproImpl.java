package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.TipoCompro;
import com.solwad.repo.ITipoComproRepo;
import com.solwad.service.ITipoComproService;
@Service
public class TipoComproImpl extends CRUDImpl<TipoCompro, Integer> implements ITipoComproService{

	@Autowired
	private ITipoComproRepo repo;
	@Override
	protected CrudRepository<TipoCompro, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	public void table_prueba() {
		repo.tc1();
		repo.tc2();
	}
	public void table_delete() {
		repo.deleten();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}
}
