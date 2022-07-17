package com.solwad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table (name = "usuario")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_user;
	@Column(name = "username", nullable = false, length = 150)
    @Size(min = 3, message = "Minimo 3 caracteres")
	public String username;
	@Column(name = "password", nullable = false, length = 500)
    @Size(min = 5, message = "Minimo 5 caracteres")
	public String password;
	@Column(name = "estado_user", nullable = false)
	public boolean estado_user;

	@ManyToOne
    @JoinColumn(name="id_rol", referencedColumnName = "id_rol")
	public Rol id_rol;

	@OneToOne
    @JoinColumn(name="dni_traba", referencedColumnName = "dni_traba")
	public Trabajador dni_traba;

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEstado_user() {
		return estado_user;
	}

	public void setEstado_user(boolean estado_user) {
		this.estado_user = estado_user;
	}

	

	public Rol getId_rol() {
		return id_rol;
	}

	public void setId_rol(Rol id_rol) {
		this.id_rol = id_rol;
	}

	public Trabajador getDni_traba() {
		return dni_traba;
	}

	public void setDni_traba(Trabajador dni_traba) {
		this.dni_traba = dni_traba;
	}

	

	 
	
	
	
}
