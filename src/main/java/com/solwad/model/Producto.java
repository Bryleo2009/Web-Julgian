package com.solwad.model;

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
@Table (name = "producto")
@Data
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id_product;
	@Column(name = "descripcion_product", nullable = false, length = 500)
    @Size(min = 3, message = "Minimo 3 caracteres")
	public String descripcion_product;
	@Column(name = "precio_uni", nullable = false)
	public double precio_uni;
	@Column(name = "stock_product", nullable = false)
	public int stock_product;
	@Column(name = "talla_product", nullable = false, length = 20)
    @Size(min = 1, message = "Minimo 1 caracteres")
	public String talla_product;
	public String imagen;

	@ManyToOne
    @JoinColumn(name="id_categ", referencedColumnName = "id_categ")
	public Categoria id_categ;

	public int getId_product() {
		return id_product;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public String getDescripcion_product() {
		return descripcion_product;
	}

	public void setDescripcion_product(String descripcion_product) {
		this.descripcion_product = descripcion_product;
	}

	public double getPrecio_uni() {
		return precio_uni;
	}

	public void setPrecio_uni(double precio_uni) {
		this.precio_uni = precio_uni;
	}

	public int getStock_product() {
		return stock_product;
	}

	public void setStock_product(int stock_product) {
		this.stock_product = stock_product;
	}

	public String getTalla_product() {
		return talla_product;
	}

	public void setTalla_product(String talla_product) {
		this.talla_product = talla_product;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Categoria getId_categ() {
		return id_categ;
	}

	public void setId_categ(Categoria id_categ) {
		this.id_categ = id_categ;
	}

	
	
	


	
	
}
