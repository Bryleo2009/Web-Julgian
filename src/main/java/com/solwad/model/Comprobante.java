package com.solwad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table (name = "comprobante")
@Data
public class Comprobante {
	@Id
        @Column(length = 50)
	public String id_comp;
	@Column(name = "nomClient_comp", nullable = false, length = 45)
    @Size(min = 3, message = "Minimo 3 caracteres")
	public String nomClient_comp; //Juan Alkexander
	@Column(name = "identClient_comp", nullable = false, length = 15)
    @Size(min = 8, message = "Minimo 8 caracteres")
	public String identClient_comp; //ruc o dni
	@Column(name = "montoSubtotal_comp", nullable = false)
	public double montoSubtotal_comp; //suma de los totales de items sin igv
	@Column(name = "montoTotal_comp", nullable = false)
	public double montoTotal_comp; //incluido igv
	@Column(name = "fechaEmi_comp", nullable = false)
	public java.sql.Date fechaEmi_comp; //21/01/2021

	@ManyToOne
    @JoinColumn(name="id_tp", referencedColumnName = "id_tp")
	public TipoPago id_tp;

	@ManyToOne
    @JoinColumn(name="id_tc", referencedColumnName = "id_tc")
	public TipoCompro id_tc;

	@ManyToOne
    @JoinColumn(name="id_user", referencedColumnName = "id_user")
	public Usuario id_user;


	public String getId_comp() {
		return id_comp;
	}

	public void setId_comp(String id_comp) {
		this.id_comp = id_comp;
	}

	public String getNomClient_comp() {
		return nomClient_comp;
	}

	public void setNomClient_comp(String nomClient_comp) {
		this.nomClient_comp = nomClient_comp;
	}

	public String getIdentClient_comp() {
		return identClient_comp;
	}

	public void setIdentClient_comp(String identClient_comp) {
		this.identClient_comp = identClient_comp;
	}

	public double getMontoSubtotal_comp() {
		return montoSubtotal_comp;
	}

	public void setMontoSubtotal_comp(double montoSubtotal_comp) {
		this.montoSubtotal_comp = montoSubtotal_comp;
	}

	public double getMontoTotal_comp() {
		return montoTotal_comp;
	}

	public void setMontoTotal_comp(double montoTotal_comp) {
		this.montoTotal_comp = montoTotal_comp;
	}

	

	public java.sql.Date getFechaEmi_comp() {
		return fechaEmi_comp;
	}

	public void setFechaEmi_comp(java.sql.Date fechaEmi_comp) {
		this.fechaEmi_comp = fechaEmi_comp;
	}

	public TipoPago getId_tp() {
		return id_tp;
	}

	public void setId_tp(TipoPago id_tp) {
		this.id_tp = id_tp;
	}

	

	public TipoCompro getId_tc() {
		return id_tc;
	}

	public void setId_tc(TipoCompro id_tc) {
		this.id_tc = id_tc;
	}

	public Usuario getId_user() {
		return id_user;
	}

	public void setId_user(Usuario id_user) {
		this.id_user = id_user;
	}


	
	
}
