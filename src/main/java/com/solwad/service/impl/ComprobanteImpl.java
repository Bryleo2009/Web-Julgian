package com.solwad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.solwad.model.Comprobante;
import com.solwad.repo.IComprobanteRepo;
import com.solwad.service.IComprobanteService;
import java.util.List;




@Service
public class ComprobanteImpl extends CRUDImpl<Comprobante, String> implements IComprobanteService{

	@Autowired
	private IComprobanteRepo repo;
	
	@Override
	protected CrudRepository<Comprobante, String> getRepo() {
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
    public List<Comprobante> ReportComprobante(String cliente) {
        return repo.ReportComprobante(cliente);
    }
    
    @Override
        public List<Comprobante> CantidadVenta(int id) {
            return repo.cantidadVentaxUsuario(id);
        }
}
