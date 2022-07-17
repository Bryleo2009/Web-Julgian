package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Trabajador;
import com.solwad.repo.ITrabajadorRepo;
import com.solwad.service.ITrabajadorService;
import java.util.List;

@Service
public class TrabajadorImpl extends CRUDImpl<Trabajador, String> implements ITrabajadorService{

	@Autowired
	private ITrabajadorRepo repo;
	@Override
	protected CrudRepository<Trabajador, String> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	public void table_prueba() {
		repo.nulo();
		repo.prueba();
	}
	public void table_delete() {
		repo.deleten();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}

    @Override
    public List<Trabajador> ReportTrabajador(String nombre) {
        return repo.ReportTrabajador(nombre);
    }

}
