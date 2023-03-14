package com.solwad.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "temporal")
public class Nick {
	@Id
	public int id_nick;
	@OneToOne
    @JoinColumn(name="usuario", referencedColumnName = "id_user")
	public Usuario usuario;
	
	
	public int getId_nick() {
		return id_nick;
	}
	public void setId_nick(int id_nick) {
		this.id_nick = id_nick;
	}
	public Usuario getUser() {
		return usuario;
	}
	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
