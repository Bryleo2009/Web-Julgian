package com.solwad.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.solwad.model.Usuario;
import com.solwad.repo.IUsuarioRepo;
import com.solwad.service.IUsuarioService;

@Service
public class UsuarioImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService, UserDetailsService{

	@Autowired
	private IUsuarioRepo repo;
	@Autowired
	private RolImpl rol;
	
	@Override
	protected CrudRepository<Usuario, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}
	
	public Usuario buscarxnombre(String username) {
		return repo.findByUsername(username);
	}
	
	public void table_prueba() {
		repo.nulo();
		repo.prueba();
	}
	public void table_delete() {
		repo.delete();
	}
	
	public void table_reinicio() {
		repo.reinicio();
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario us =  repo.findByUsername(username);
		List<GrantedAuthority> roles = new ArrayList<>();
		
		roles.add(new SimpleGrantedAuthority(us.getId_rol().getNombre_rol()));

		UserDetails userDet = new User(us.getUsername(), us.getPassword(),roles);
		return userDet;
	}
	

}
