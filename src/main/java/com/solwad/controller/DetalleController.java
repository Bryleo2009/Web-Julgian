package com.solwad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.solwad.model.Detalle;
import com.solwad.model.Nick;
import com.solwad.model.Producto;
import com.solwad.service.IDetalleService;
import com.solwad.service.impl.ComprobanteImpl;
import com.solwad.service.impl.DetalleImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.ProductoImpl;

@Controller
@RequestMapping("/detalles")
public class DetalleController {
	@Autowired
	private DetalleImpl service;
	List<Detalle> detalle = null;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();
	@Autowired
	private ProductoImpl serviceProduc;	
        List<Producto> productos = null;
        @Autowired
	private ComprobanteImpl service_Compro;

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("activo", "comprobantes");
	}

	@GetMapping("")
	public String detalles(Model model) {
		detalle = service.listDetalle();
		n = nick.ListarId(1);
                productos=serviceProduc.listar();
                model.addAttribute("producto",productos);
                model.addAttribute("detalles", detalle);
		model.addAttribute("nick",n);
		return "comprobante/ListaDetalle";
	}

	//
	public String list_detalle(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "";
	}

	//
	@RequestMapping(value = "/agregar",method = RequestMethod.POST)
	public String reg_detalle(@RequestParam("imagen") String imagen,
			@RequestParam("id_product") int id_product,
            @RequestParam("talla_product") String talla_product,
            @RequestParam("desc_product") String nom_pro,
            @RequestParam("precio_unit") double precio_uni,
            @RequestParam("cantidad") int cantidad,Model model) {
		//Calcular precio Total
				n = nick.ListarId(1);
				
				if(cantidad>0) {
					double precio_Total = precio_uni*cantidad;
					//agregar detalle con cod_comp_temp
					Detalle d = new Detalle();
					d.setImagen(imagen);
					d.setProducto_detalle(nom_pro + "/talla: "+talla_product);
					d.setPrecioUni_detalle(precio_uni);
					d.setCantProduct_detalle(cantidad);
					d.setId_comp(service_Compro.ListarId("N00000"));
					d.setPrecioTotal_detalle(Math.round(precio_Total*100.0)/100.0);
					d.setId_product(id_product);
					service.registrar(d);
					
					Producto p = serviceProduc.ListarId(id_product);
					p.setStock_product(p.getStock_product()-cantidad);
					serviceProduc.modificar(p);
					
					
					
				}		
				
				//listar productos
				productos = serviceProduc.listar();
				model.addAttribute("productos",productos);
		model.addAttribute("nick",n);
		return "comprobante/ListaProducto_Compro";
	}

	//
	@GetMapping("/elim_detalle")
	public String elim_detalle(@RequestParam("id_dcomp") int id_dcomp,Model model) {
		int p = 0;
		p = service.ListarId(id_dcomp).getId_product();
		Detalle d = service.ListarId(id_dcomp); 
		Producto producto = serviceProduc.ListarId(p);
		producto.setStock_product(producto.getStock_product()+d.getCantProduct_detalle());
		serviceProduc.modificar(producto);
		
		
		service.eliminar(id_dcomp);
		n = nick.ListarId(1);
		model.addAttribute("nick",n);
		return "redirect:/detalles";
	}

	@RequestMapping(value = "/contar",method = RequestMethod.POST)
	public String contar(
			@RequestParam("id_dcomp") int id_dcomp,
			@RequestParam("cantidad") int cantidad,
			Model model) {
		Detalle d = new Detalle();
		d = service.ListarId(id_dcomp);
		d.setCantProduct_detalle(cantidad);
		service.modificar(d);
		n = nick.ListarId(1);
		model.addAttribute("nick",n);
		return "redirect:/detalles";
	}

	
	@GetMapping("/cancelar")
	public String modi_detalle(Model model) {
		detalle = service.listDetalle();
		for(int i=0; i<detalle.size();i++) {
			Detalle d = detalle.get(i);
			Producto p = serviceProduc.ListarId(d.getId_product());
			p.setStock_product(d.getCantProduct_detalle()+p.getStock_product());
			serviceProduc.modificar(p);
		}
		
		
		
        service.borrado();
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "redirect:/comprobantes";
	}

}
