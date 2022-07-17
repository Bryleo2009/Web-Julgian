package com.solwad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.solwad.model.Nick;
import com.solwad.model.Rol;
import com.solwad.service.IRolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RolController {

	@Autowired
	private IRolService service;
	List<Rol> roles = null;
	Nick n = new Nick();

	//
	@GetMapping("")
	public String list_rol(Model model) {
		roles = service.listar();
		model.addAttribute("roles", roles);
		model.addAttribute("nick", n);
		return "usuario/ListaRol";
	}

	//
	@GetMapping("/new_rol")
	public String new_rol(Model model) {
		model.addAttribute("nick", n);
		return "usuario/FormRol";
	}

	//
	@RequestMapping(value = "/regis_rol", method = RequestMethod.POST)
	public String regis_rol(@RequestParam("nombre_rol") String nombre_rol, Model model) {
		Rol r = new Rol();
		r.setNombre_rol(nombre_rol);
		service.registrar(r);
		roles = service.listar();
		model.addAttribute("roles", roles);
		model.addAttribute("nick", n);
		return "redirect:/roles";
	}

	//
	@GetMapping("/eliminar_rol")
	public String elim_rol(@RequestParam("id_rol") int id_rol, Model model) {
		service.eliminar(id_rol);
		roles = service.listar();
		model.addAttribute("roles", roles);
		model.addAttribute("nick", n);
		return "redirect:/roles";
	}

	//
	@GetMapping("/edit_rol")
	public String modi_rol(@RequestParam(value = "id_rol") int id_rol, Model model) {
		Rol rol = service.ListarId(id_rol);
		model.addAttribute("rol", rol);
		model.addAttribute("nick", n);
		return "usuario/ModiRol";
	}

	//
	@RequestMapping(value = "/actu_rol", method = RequestMethod.POST)
	public String act_rol(
			@RequestParam("id_rol") int id_rol,
			@RequestParam("nombre_rol") String nombre_rol, Model model) {
		Rol r = new Rol();
		r.setId_rol(id_rol);
		r.setNombre_rol(nombre_rol);
		service.modificar(r);
		roles = service.listar();
		model.addAttribute("roles", roles);
		model.addAttribute("nick", n);
		return "redirect:/roles";
	}
}
