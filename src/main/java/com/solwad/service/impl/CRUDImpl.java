package com.solwad.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.solwad.service.ICRUDService;



public abstract class CRUDImpl<T, ID> implements ICRUDService<T,ID> {

	protected abstract CrudRepository<T, ID> getRepo();
	
	@Override
	public void registrar(T t) {
		// TODO Auto-generated method stub
		getRepo().save(t);
	}

	@Override
	public void modificar(T t) {
		// TODO Auto-generated method stub
		getRepo().save(t);
	}

	@Override
	public T ListarId(ID id) {
		// TODO Auto-generated method stub
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public List<T> listar() {
		// TODO Auto-generated method stub
		return (List<T>) getRepo().findAll();
	}

	@Override
	public void eliminar(ID id) {
		// TODO Auto-generated method stub
		getRepo().deleteById(id);
	}

}
