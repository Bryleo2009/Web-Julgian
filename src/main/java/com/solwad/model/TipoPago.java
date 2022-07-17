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
@Table (name = "tipo_pago")
@Data
public class TipoPago {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_tp;
    @Column(name = "nombre_tp", nullable = false, length = 20)
    @Size(min = 3, message = "Minimo 3 caracteres")
    public String nombre_tp;
	public int getId_tp() {
		return id_tp;
	}
	public void setId_tp(int id_tp) {
		this.id_tp = id_tp;
	}
	public String getNombre_tp() {
		return nombre_tp;
	}
	public void setNombre_tp(String nombre_tp) {
		this.nombre_tp = nombre_tp;
	}
}
