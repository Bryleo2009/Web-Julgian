package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Categoria;
import com.solwad.repo.ICategoriaRepo;
import com.solwad.service.ICategoriaService;
@Service
public class CategoriaImpl extends CRUDImpl<Categoria, Integer> implements ICategoriaService{

	@Autowired
	private ICategoriaRepo repo;
	@Override
	protected CrudRepository<Categoria, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	public void table_delete() {
		repo.deletecategoria();
	}
	public void table_reinicio() {
		repo.reiniciocategoria();
	}
	public void table_prueba() {
		repo.createcategoria();
		repo.prueba();
	}
	

}
