package com.solwad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "categoria_product")
@Data
public class Categoria {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_categ;
    @Column(name = "nombre_categ", nullable = false, length = 20)
    @Size(min = 3, message = "Minimo 3 caracteres")
    public String nombre_categ;

	public int getId_categ() {
		return id_categ;
	}
	public void setId_categ(int id_categ) {
		this.id_categ = id_categ;
	}
	public String getNombre_categ() {
		return nombre_categ;
	}
	public void setNombre_categ(String nombre_categ) {
		this.nombre_categ = nombre_categ;
	}

}
