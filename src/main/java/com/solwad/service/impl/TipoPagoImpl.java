package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.TipoPago;
import com.solwad.repo.ITipoPagoRepo;
import com.solwad.service.ITipoPagoService;
@Service
public class TipoPagoImpl extends CRUDImpl<TipoPago, Integer> implements ITipoPagoService{

	@Autowired
	private ITipoPagoRepo repo;
	@Override
	protected CrudRepository<TipoPago, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	public void table_prueba() {
		repo.tp1();
		repo.tp2();
	}
	public void table_delete() {
		repo.deleten();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}

}
