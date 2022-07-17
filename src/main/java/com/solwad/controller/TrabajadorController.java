package com.solwad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.solwad.model.Comprobante;
import com.solwad.model.Nick;
import com.solwad.model.Trabajador;
import com.solwad.model.Usuario;
import com.solwad.service.IDetalleService;
import com.solwad.service.ITrabajadorService;
import com.solwad.service.impl.ComprobanteImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.TrabajadorImpl;
import com.solwad.service.impl.UsuarioImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/trabajadores")
public class TrabajadorController {

	@Autowired
	private TrabajadorImpl service;
	List<Trabajador> trabajadores = null;
	@Autowired
	private UsuarioImpl user;
	@Autowired
	private NickImpl nick;
	@Autowired
	private ComprobanteImpl compro;
	Nick n = new Nick();

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("activo", "trabajadores");
	}

	//
	@GetMapping("")
	public String list_traba(Model model) {
		trabajadores = service.listar();
		n = nick.ListarId(1);
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("nick", n);
		return "usuario/ListaTrabajador";
	}

	//
	@GetMapping("/new_traba")
	public String new_traba(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "usuario/FormTrabajador";
	}

	//
	@RequestMapping(value = "/regis_traba", method = RequestMethod.POST)
	public String regis_traba(
			@RequestParam("dni_traba") String dni_traba,
			@RequestParam("telef_traba") String telef_traba,
			@RequestParam("nombre_traba") String nombre_traba,
			@RequestParam("apellido_traba") String apellido_traba,
			@RequestParam("direccion_traba") String direccion_traba,
			@RequestParam("fecnac_traba") Date fecnac_traba,
			@RequestParam("file") MultipartFile imagen,
			Model model) {
		Trabajador t = new Trabajador();
		if(!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("scr//main//resources//static/img");
			String rutaAbsoluta = "C://solwad//recursos";
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				t.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		String recon = null;
		n = nick.ListarId(1);
		
		t.setDni_traba(dni_traba);
		t.setTelefono_traba(telef_traba);
		t.setNombre_traba(nombre_traba);
		t.setApellido_traba(apellido_traba);
		t.setDireccion_traba(direccion_traba);
		t.setFechaNac_traba(fecnac_traba);
		
		service.registrar(t);
		trabajadores = service.listar();
		model.addAttribute("registro", t);
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("nick", n);
		return "redirect:/usuarios/new_users/" + t.getDni_traba();
	}

	//
	@GetMapping("/eliminar_traba")
	public String elim_traba(@RequestParam("dni_traba") String dni_traba, Model model) {
		n = nick.ListarId(1);
		for (int i = 0; i < user.listar().size(); i++) {
			if (user.listar().get(i).getDni_traba().getDni_traba().equals(dni_traba)) {
				for (int j = 0; j < compro.listar().size(); j++) {
					if (compro.listar().get(j).getId_user().getId_user() == user.listar().get(i).getId_user()) {
						Comprobante c = new Comprobante();
						Usuario u = new Usuario();
						u = user.ListarId(1); // mi usuario de respaldo "null" siempre tiene que ser el numero 1
						c = compro.ListarId(compro.listar().get(j).getId_comp());
						c.setId_user(u);
						compro.modificar(c);
					}
				}
				user.eliminar(user.listar().get(i).getId_user());
			}
		}
		service.eliminar(dni_traba);
		trabajadores = service.listar();
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("nick", n);
		return "redirect:/trabajadores";
	}

	//
	@GetMapping("/edit_traba")
	public String modi_traba(@RequestParam(value = "dni_traba") String dni_traba, Model model) {
		n = nick.ListarId(1);
		Trabajador trabajador = service.ListarId(dni_traba);
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("nick", n);
		return "usuario/ModiTrabajador";
	}

	//
	@RequestMapping(value = "/actu_traba", method = RequestMethod.POST)
	public String act_traba(
			@RequestParam("antigua") String antigua,
			@RequestParam("dni_traba") String dni_traba,
			@RequestParam("telefono_traba") String telef_traba,
			@RequestParam("nombre_traba") String nombre_traba,
			@RequestParam("apellido_traba") String apellido_traba,
			@RequestParam("direccion_traba") String direccion_traba,
			@RequestParam("fecNac_traba") Date fecnac_traba,
			@RequestParam("file") MultipartFile imagen,
			Model model) {
		Trabajador t = new Trabajador();
		if(!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("scr//main//resources//static/img");
			String rutaAbsoluta = "C://solwad//recursos";
			//String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				t.setImagen(imagen.getOriginalFilename());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(imagen.isEmpty()) {
			t.setImagen(antigua);
		}	
		n = nick.ListarId(1);
		t.setDni_traba(dni_traba);
		t.setTelefono_traba(telef_traba);
		t.setNombre_traba(nombre_traba);
		t.setApellido_traba(apellido_traba);
		t.setDireccion_traba(direccion_traba);
		t.setFechaNac_traba(fecnac_traba);
		service.modificar(t);
		trabajadores = service.listar();
		model.addAttribute("trabajadores", trabajadores);
		model.addAttribute("nick", n);
		return "redirect:/trabajadores";
	}

	//
	@GetMapping("/detalle_traba")
	public String detalle_traba(@RequestParam(value = "dni_traba") String dni_traba, Model model) {
		n = nick.ListarId(1);
		Trabajador trabajador = service.ListarId(dni_traba);
		model.addAttribute("trabajador", trabajador);
		model.addAttribute("nick", n);
		return "usuario/DetTrabajador";
	}
	// me lleva a la lista de empleados para un nuevo usuario
	/*
	 * @GetMapping("/list_emple2")
	 * public String list_emple2 (
	 * 
	 * @RequestParam(value = "name_trab") String name_trab,
	 * 
	 * @RequestParam(value = "id_user") int id_user,
	 * 
	 * @RequestParam(value = "id_rol") int id_rol,
	 * 
	 * @RequestParam(value = "id_trab") int id_trab,
	 * 
	 * @RequestParam(value = "name_user") String name_user,
	 * 
	 * @RequestParam(value = "name_rol") String name_rol,
	 * Model model) {
	 * n.setId_rol(id_rol);
	 * n.setId_trab(id_trab);
	 * n.setId_user(id_user);
	 * n.setName_rol(name_rol);
	 * n.setName_trab(name_trab);
	 * n.setName_user(name_user);
	 * trabajadores = service.listar();
	 * model.addAttribute("empleados",trabajadores);
	 * model.addAttribute("nick",n);
	 * return "ListaEmpleados2";
	 * }
	 */

}
