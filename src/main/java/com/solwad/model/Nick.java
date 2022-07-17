package com.solwad.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "temp")
public class Nick {
	@Id
	public int id_nick;
	@OneToOne
    @JoinColumn(name="user", referencedColumnName = "id_user")
	public Usuario user;
	
	
	public int getId_nick() {
		return id_nick;
	}
	public void setId_nick(int id_nick) {
		this.id_nick = id_nick;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	
	
	
}
