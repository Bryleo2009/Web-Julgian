package com.solwad.controller;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solwad.model.Categoria;
import com.solwad.model.Comprobante;
import com.solwad.model.Nick;
import com.solwad.model.Rol;
import com.solwad.model.Trabajador;
import com.solwad.model.Usuario;
import com.solwad.service.IRolService;
import com.solwad.service.ITrabajadorService;
import com.solwad.service.IUsuarioService;
import com.solwad.service.impl.ComprobanteImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.RolImpl;
import com.solwad.service.impl.TrabajadorImpl;
import com.solwad.service.impl.UsuarioImpl;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioImpl service;
	@Autowired
	private RolImpl rol;
	@Autowired
	private TrabajadorImpl traba;
	@Autowired
	private ComprobanteImpl compro;
	@Autowired
	private BCryptPasswordEncoder encoder;
	List<Usuario>usuarios = null;
	List<Rol>roles = null;
	List<Trabajador>trabajadores = null;
	
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	@ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("activo", "usuarios");
    }
	
	//
	@GetMapping("")
	public String list_user (Model model) {
		n = nick.ListarId(1);
		usuarios = service.listar();
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("nick",n);
		return "usuario/ListaUsuario";
	}
	//
	@GetMapping("/new_user")
	public String new_categ (Model model) {
		usuarios = service.listar();
		n = nick.ListarId(1);
		roles = rol.listar();
		trabajadores = traba.listar();
		String recon = "";
		model.addAttribute("recon",recon);
		model.addAttribute("roles",roles);
		model.addAttribute("trabajadores",trabajadores);
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("nick",n);
		return "usuario/FormUsuario";
	}
	
	//eliminar tildes de los textos
	public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
	
	//lo uso despues de crear un nuevo trabajador
	@GetMapping("/new_users/{dni_traba}")
	public String new_categ (@PathVariable("dni_traba") String dni_traba,Model model) {
		n = nick.ListarId(1);
		usuarios = service.listar();
		roles = rol.listar();
		Trabajador trabajadores = traba.ListarId(dni_traba);
		String recon = "";
		String cadena = trabajadores.apellido_traba;
		cadena = cadena.trim();
		String[] nueva = cadena.split("\\s+");
		int x = 0,y = 0;
		if(trabajadores.fechaNac_traba.getDay()>trabajadores.fechaNac_traba.getMonth()) {
			x = trabajadores.fechaNac_traba.getDay();
			y = trabajadores.fechaNac_traba.getMonth();
		}
		if(trabajadores.fechaNac_traba.getDay()<trabajadores.fechaNac_traba.getMonth()) {
			y = trabajadores.fechaNac_traba.getDay();
			x = trabajadores.fechaNac_traba.getMonth();
		}		
		int ale = (int)(Math.random()*(x-y+1)+y);
		System.out.println(ale);
		System.out.println(x);
		System.out.println(y);
		System.out.println(ale);
		if(ale<0) {ale = ale * (-1);}
		recon = cleanString(trabajadores.nombre_traba.substring(0,1).toLowerCase()) + 
				cleanString(nueva[0].toLowerCase()) + ale;
		model.addAttribute("recon",recon);
		model.addAttribute("roles",roles);
		model.addAttribute("trabajadores",trabajadores);
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("nick",n);
		return "usuario/FormUsuario";
	}
	
	@RequestMapping(value="/regis_user", method = RequestMethod.POST)
	public String reg_user (
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("id_rol") Rol id_rol,
			@RequestParam("id_traba") Trabajador id_traba,
			Model model) {
		Usuario u = new Usuario();
		n = nick.ListarId(1);
		u.setUsername(username);
		u.setPassword(encoder.encode(password));
		u.setId_rol(id_rol);
		u.setEstado_user(true);
		u.setDni_traba(id_traba);
		service.registrar(u);
		usuarios = service.listar();
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("nick",n);
		return "redirect:/usuarios";
	}
	//
	@GetMapping("/eliminar_user")
	public String elim_user (@RequestParam(value = "id_user") int id_user,Model model) {

			
				for (int j = 0; j < compro.listar().size(); j++) {
					if (compro.listar().get(j).getId_user().getId_user() == id_user) {
						Comprobante c = new Comprobante();
						Usuario u = new Usuario();
						u = service.ListarId(1); // mi usuario de respaldo "null" siempre tiene que ser el numero 1
						c = compro.ListarId(compro.listar().get(j).getId_comp());
						c.setId_user(u);
						compro.modificar(c);
					}
				}

		
		
		service.eliminar(id_user);
		n = nick.ListarId(1);
		usuarios = service.listar();
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("nick",n);
		return "redirect:/usuarios";
	}
	//
	@GetMapping("/edit_user")
	public String modi_user (@RequestParam(value = "id_user") int id_user,Model model) {
		Usuario usuario = service.ListarId(id_user);
		n = nick.ListarId(1);
		roles = rol.listar();
		trabajadores = traba.listar();
		model.addAttribute("usuario",usuario);
		model.addAttribute("roles",roles);
		model.addAttribute("trabajadores",trabajadores);
		model.addAttribute("nick",n);
		return "usuario/ModiUsuario";
	}
	//
	@RequestMapping(value = "/actualizar_user", method = RequestMethod.POST)
	public String act_user(
			@RequestParam("id_user") int id_user,
			@RequestParam("estado_user") boolean estado_user,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("id_rol") Rol id_rol,
			@RequestParam("id_traba") Trabajador id_traba,
			Model model) {
		Usuario u = new Usuario();
		n = nick.ListarId(1);
		u.setDni_traba(id_traba);
		u.setEstado_user(estado_user);
		u.setId_rol(id_rol);
		u.setId_user(id_user);
		u.setPassword(password);
		u.setUsername(username);
		service.modificar(u);
		usuarios = service.listar();
		model.addAttribute("usuario",usuarios);
		model.addAttribute("nick",n);
		return "redirect:/usuarios";
	}
	
	

	//aplicacion del login
	/*@GetMapping("/")
	public String login(Model model) {
		boolean sistUser=false;
		boolean sistRol=false;
		boolean sistTrab=false;
		int id_rol=0, id_trab=0;
		usuarios = service.listar();
		roles = rol.listar();
		trabajadores = trab.listar();
		for(int i=0;i<usuarios.size();i++) {
			if(usuarios.get(i).getName_user().equals("admin_bd")) sistUser = true;
		}
		for(int i=0;i<roles.size();i++) {
			if(roles.get(i).getNombre_rol().equals("admin_bd")) sistRol = true;
		}
		for(int i=0;i<trabajadores.size();i++) {
			if(trabajadores.get(i).getNombre_trab().equals("OfSystem")) sistTrab = true;
		}
		if(sistRol == false) {
			System.out.println("Creando datos rol");
			Rol r = new Rol(); r.setNombre_rol("admin_bd");
			rol.registrar(r);
			roles = rol.listar();
		}
		if(sistTrab == false) {
			System.out.println("Creando datos trabajador");
			Trabajador t = new Trabajador();t.setNombre_trab("OfSystem");
			trab.registrar(t);
			trabajadores = trab.listar();
		}
		if(sistUser == false) {
			System.out.println("Creando datos usuario");
			for(int i=0; i<rol.listar().size();i++) {
				if(rol.listar().get(i).getNombre_rol().equals("admin_bd"))id_rol = rol.listar().get(i).getId_rol();
			}
			for(int i=0; i<trab.listar().size();i++) {
				if(trab.listar().get(i).getNombre_trab().equals("OfSystem"))id_trab = trab.listar().get(i).getId_trab();
			}
			Usuario u = new Usuario(); 			 
			u.setName_user("admin_bd"); 
			u.setPass_user("2022"); 
			u.setEstado_user(true);
			u.setId_traba(id_trab);
			u.setId_rol(id_rol);
			service.registrar(u);
			usuarios = service.listar();
		}	
		
		System.out.println("--------------");
		System.out.println("Usuarios de BD");
		//
		for(int i = 0; i<usuarios.size();i++) {
			System.out.println(usuarios.get(i).getName_user() + " - " + usuarios.get(i).getPass_user());
		}
		//
		//model.addAttribute("usuarios",usuarios);//agrego lista de base de datos		
		return "login";
	}*/

	
}
