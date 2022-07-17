package com.solwad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table (name = "trabajador")
@Data
public class Trabajador {
	@Id
	@Size(min = 8, message = "Minimo 8 caracteres")
	public String dni_traba;
	@Column(name = "nombre_traba", nullable = false, length = 300)
    @Size(min = 3, message = "Minimo 3 caracteres")
	public String nombre_traba;
	@Column(name = "apellido_traba", nullable = false, length = 300)
    @Size(min = 3, message = "Minimo 3 caracteres")
	public String apellido_traba;
	@Column(name = "fechaNac_traba")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "GMT-5")
	public java.sql.Date fechaNac_traba;
	@Column(name = "telefono_traba",  length = 9)
	public String telefono_traba;
	@Column(name = "direccion_traba", length = 45)
	public String direccion_traba;
	public String imagen;

	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDni_traba() {
		return dni_traba;
	}
	public void setDni_traba(String dni_traba) {
		this.dni_traba = dni_traba;
	}
	public String getNombre_traba() {
		return nombre_traba;
	}
	public void setNombre_traba(String nombre_traba) {
		this.nombre_traba = nombre_traba;
	}
	public String getApellido_traba() {
		return apellido_traba;
	}
	public void setApellido_traba(String apellido_traba) {
		this.apellido_traba = apellido_traba;
	}
	/*public Date getFechaNac_traba() {
		return fechaNac_traba;
	}
	public void setFechaNac_traba(Date fechaNac_traba) {
		this.fechaNac_traba = fechaNac_traba;
	}*/
	
	public String getTelefono_traba() {
		return telefono_traba;
	}
	public java.sql.Date getFechaNac_traba() {
		return fechaNac_traba;
	}
	public void setFechaNac_traba(java.sql.Date fechaNac_traba) {
		this.fechaNac_traba = fechaNac_traba;
	}
	public void setTelefono_traba(String telefono_traba) {
		this.telefono_traba = telefono_traba;
	}
	public String getDireccion_traba() {
		return direccion_traba;
	}
	public void setDireccion_traba(String direccion_traba) {
		this.direccion_traba = direccion_traba;
	}

	
}
