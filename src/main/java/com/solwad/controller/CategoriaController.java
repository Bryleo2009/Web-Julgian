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

import com.solwad.model.Categoria;
import com.solwad.model.Nick;
import com.solwad.model.Producto;
import com.solwad.service.ICategoriaService;
import com.solwad.service.IProductoService;
import com.solwad.service.impl.CategoriaImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.ProductoImpl;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaImpl service;
	@Autowired
	private ProductoImpl product;
	List<Categoria> categorias = null;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("activo", "categorias");
	}

	//
	@GetMapping("")
	public String list_categ(Model model) {
		n = nick.ListarId(1);
		categorias = service.listar();
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "producto/ListaCategoria";
	}

	@GetMapping("/new_categ")
	public String new_categ(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "producto/FormCategoria";
	}

	//
	@RequestMapping(value = "/regis_categ", method = RequestMethod.POST)
	public String reg_categ(
			@RequestParam("nombre_categ") String nombre_categ,
			Model model) {
		n = nick.ListarId(1);
		Categoria c = new Categoria();
		c.setNombre_categ(nombre_categ);
		service.registrar(c);
		categorias = service.listar();
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "redirect:/categorias";
	}

	//
	@GetMapping("/eliminar_categ")
	public String elim_categ(@RequestParam(value = "id_categ") int id_categ, Model model) {
		List<Producto> productos = product.listar();
		n = nick.ListarId(1);
		Producto p = new Producto();
		Categoria c = new Categoria();
		c = service.ListarId(1); // le pasos datos de la categoria nula
		for (int i = 0; i < productos.size(); i++) {
			if (productos.get(i).id_categ.id_categ == id_categ) { // modifico los productos que poseen esa categ
				p = product.ListarId(i + 1);
				p.setId_product(i+1);
				p.setId_categ(c);
				product.registrar(p);// guardo datos modificados
			}
		}
		service.eliminar(id_categ);
		categorias = service.listar();
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "redirect:/categorias";
	}

	//
	@GetMapping("/edit_categ")
	public String modi_categ(@RequestParam(value = "id_categ") int id_categ, Model model) {
		Categoria categoria = service.ListarId(id_categ);
		n = nick.ListarId(1);
		model.addAttribute("categoria", categoria);
		model.addAttribute("nick", n);
		return "producto/ModiCategoria";
	}

	//
	@RequestMapping(value = "/actualizar_categ", method = RequestMethod.POST)
	public String act_categ(
			@RequestParam("id_categ") int id_categ,
			@RequestParam("nombre_categ") String nombre_categ,
			Model model) {
		Categoria c = new Categoria();
		n = nick.ListarId(1);
		c.setId_categ(id_categ);
		c.setNombre_categ(nombre_categ);
		service.modificar(c);
		categorias = service.listar();
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "redirect:/categorias";
	}
}
