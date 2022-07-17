package com.solwad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "detalle_comprobante")
@Data
public class Detalle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_dcomp;
	@Column(name = "cantProduct_detalle", nullable = false)
	public int cantProduct_detalle; //10
	@Column(name = "precioUni_detalle", nullable = false)
	public double precioUni_detalle; //0.20
	@Column(name = "precioTotal_detalle", nullable = false)
	public double precioTotal_detalle; //2.00
	@Column(name = "producto_detalle", nullable = false)
	public String producto_detalle;
	public int id_product;
	public String imagen;
	@ManyToOne
    @JoinColumn(name="id_comp", referencedColumnName = "id_comp")
	public Comprobante id_comp;
	

	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getId_product() {
		return id_product;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public int getId_dcomp() {
		return id_dcomp;
	}
	public void setId_dcomp(int id_dcomp) {
		this.id_dcomp = id_dcomp;
	}
	public int getCantProduct_detalle() {
		return cantProduct_detalle;
	}
	public void setCantProduct_detalle(int cantProduct_detalle) {
		this.cantProduct_detalle = cantProduct_detalle;
	}
	public double getPrecioUni_detalle() {
		return precioUni_detalle;
	}
	public void setPrecioUni_detalle(double precioUni_detalle) {
		this.precioUni_detalle = precioUni_detalle;
	}
	public double getPrecioTotal_detalle() {
		return precioTotal_detalle;
	}
	public void setPrecioTotal_detalle(double precioTotal_detalle) {
		this.precioTotal_detalle = precioTotal_detalle;
	}
	public Comprobante getId_comp() {
		return id_comp;
	}
	public void setId_comp(Comprobante id_comp) {
		this.id_comp = id_comp;
	}
	public String getProducto_detalle() {
		return producto_detalle;
	}
	public void setProducto_detalle(String producto_detalle) {
		this.producto_detalle = producto_detalle;
	}
	
	
	
	
	
}
