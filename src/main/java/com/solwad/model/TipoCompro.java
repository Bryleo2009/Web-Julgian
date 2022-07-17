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
@Table (name = "tipo_compro")
@Data
public class TipoCompro {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_tc;
    @Column(name = "nombre_tc", nullable = false, length = 20)
    @Size(min = 3, message = "Minimo 3 caracteres")
    public String nombre_tc;

	public int getId_tc() {
		return id_tc;
	}
	public void setId_tc(int id_tc) {
		this.id_tc = id_tc;
	}
	public String getNombre_tc() {
		return nombre_tc;
	}
	public void setNombre_tc(String nombre_tc) {
		this.nombre_tc = nombre_tc;
	}
}
