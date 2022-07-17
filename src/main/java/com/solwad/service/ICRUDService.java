package com.solwad.service;

import java.util.List;
import java.util.Optional;


public interface ICRUDService<T,ID> {

	void registrar(T t);
	void modificar(T t);
	T ListarId(ID id);
	List<T> listar();
	void eliminar(ID id);
	
}
