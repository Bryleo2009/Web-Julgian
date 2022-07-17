package com.solwad.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.solwad.model.Categoria;
import com.solwad.model.Nick;
import com.solwad.model.Producto;
import com.solwad.service.ICategoriaService;
import com.solwad.service.IProductoService;
import com.solwad.service.impl.CategoriaImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.ProductoImpl;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoImpl service;
	@Autowired
	private CategoriaImpl cate;
	List<Producto> productos = null;
	List<Categoria> categorias = null;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("activo", "productos");
	}

	//
	@GetMapping("")
	public String list_produc(Model model) {
		productos = service.listar();
		n = nick.ListarId(1);
		model.addAttribute("productos", productos);
		model.addAttribute("nick", n);
		return "producto/ListaProducto";
	}

	@GetMapping("/new_product")
	public String new_product(Model model) {
		n = nick.ListarId(1);
		categorias = cate.listar();
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "producto/FormProducto";
	}

	//

	@RequestMapping(value = "/regis_product", method = RequestMethod.POST)
	public String reg_produc(
			@RequestParam("descripcion_product") String descripcion_product,
			@RequestParam("precio_product") double precio_product,
			@RequestParam("stock_product") int stock_product,
			@RequestParam("talla_product") String talla_product,
			@RequestParam("cate") Categoria cate,
			@RequestParam("file") MultipartFile imagen,
			Model model) {
		
		Producto p = new Producto();
		if(!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("scr//main//resources//static/img");
			String rutaAbsoluta = "C://solwad//recursos";
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				p.setImagen(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		n = nick.ListarId(1);
		p.setDescripcion_product(descripcion_product);
		p.setId_categ(cate);
		p.setPrecio_uni(Math.round(precio_product * 100.0) / 100.0);
		p.setStock_product(stock_product);
		p.setTalla_product(talla_product);
		service.registrar(p);
		productos = service.listar();
		model.addAttribute("productos", productos);
		model.addAttribute("nick", n);
		return "redirect:/productos";
	}

	//
	@GetMapping("/eliminar_product")
	public String elim_produc(@RequestParam(value = "id_product") int id_product, Model model) {
		service.eliminar(id_product);
		n = nick.ListarId(1);
		productos = service.listar();
		model.addAttribute("productos", productos);
		model.addAttribute("nick", n);
		return "redirect:/productos";
	}

	//
	@GetMapping("/edit_product")
	public String modi_produc(@RequestParam(value = "id_product") int id_product, Model model) {
		Producto producto = service.ListarId(id_product);
		n = nick.ListarId(1);
		categorias = cate.listar();
		model.addAttribute("producto", producto);
		model.addAttribute("categorias", categorias);
		model.addAttribute("nick", n);
		return "producto/ModiProducto";
	}

	//
	@RequestMapping(value = "/actualizar_product", method = RequestMethod.POST)
	public String act_produc(
			@RequestParam("antigua") String antigua,
			@RequestParam("id_product") int id_product,
			@RequestParam("descripcion_product") String descripcion_product,
			@RequestParam("precio_uni") double precio_uni,
			@RequestParam("stock_product") int stock_product,
			@RequestParam("talla_product") String talla_product,
			@RequestParam("cate") Categoria cate,
			@RequestParam("file") MultipartFile imagen,
			Model model) {
		
		Producto p = new Producto();
		if(!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("scr//main//resources//static/img");
			String rutaAbsoluta = "C://solwad//recursos";
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				p.setImagen(imagen.getOriginalFilename());
				System.out.println("imagen nueva:" + imagen.getOriginalFilename());
				System.out.println(p.getImagen());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(imagen.isEmpty()) {
			p.setImagen(antigua);
		}	
		
		n = nick.ListarId(1);
		p.setDescripcion_product(descripcion_product);
		p.setId_categ(cate);
		p.setId_product(id_product);
		p.setPrecio_uni(Math.round(precio_uni * 100.0) / 100.0);
		p.setStock_product(stock_product);
		p.setTalla_product(talla_product);
		service.modificar(p);
		productos = service.listar();
		
		model.addAttribute("productos", productos);
		model.addAttribute("nick", n);
		return "redirect:/productos";
	}

	//
	
}
