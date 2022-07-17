package com.solwad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import com.solwad.model.Detalle;
import com.solwad.repo.IDetalleRepo;
import com.solwad.service.IDetalleService;




@Service
public class DetalleImpl extends CRUDImpl<Detalle, Integer> implements IDetalleService{

	@Autowired
	private IDetalleRepo repo;
	
	@Override
	protected CrudRepository<Detalle, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	public void table_prueba() {
		repo.prueba();
	}
	public void table_delete() {
		repo.deleten();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}
	public List<Detalle> listDetalle(){
        return repo.listDetalle();
	}
	public List<Detalle> lista_det_Comp(String id_comp){
	        return repo.lista_det_Comp(id_comp);
	}
	public void borrado() {
		 repo.borrado();
	}
}
