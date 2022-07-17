package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Producto;
import com.solwad.repo.IProductoRepo;
import com.solwad.service.IProductoService;
import java.util.List;

@Service
public class ProductoImpl extends CRUDImpl<Producto, Integer> implements IProductoService{

	@Autowired
	private IProductoRepo repo;
	
	@Override
	protected CrudRepository<Producto, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	public void table_delete() {
		repo.deleteproducto();
	}
	
	public void table_reinicio() {
		repo.reinicioproducto();
	}
	
	public void table_prueba() {
		repo.nulo();
		repo.prueba();
	}

    @Override
    public List<Producto> ReportProduct(String nombre, String cate) {
        return repo.ReportProduct(nombre,cate);
    }
    
}
