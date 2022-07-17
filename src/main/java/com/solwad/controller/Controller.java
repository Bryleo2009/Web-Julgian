package com.solwad.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solwad.model.Categoria;
import com.solwad.model.Comprobante;
import com.solwad.model.Detalle;
import com.solwad.model.Nick;
import com.solwad.model.Producto;
import com.solwad.model.Trabajador;
import com.solwad.model.Usuario;
import com.solwad.service.ICategoriaService;
import com.solwad.service.IProductoService;
import com.solwad.service.impl.CategoriaImpl;
import com.solwad.service.impl.ComprobanteImpl;
import com.solwad.service.impl.DetalleImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.ProductoImpl;
import com.solwad.service.impl.RolImpl;
import com.solwad.service.impl.TipoComproImpl;
import com.solwad.service.impl.TipoPagoImpl;
import com.solwad.service.impl.TrabajadorImpl;
import com.solwad.service.impl.UsuarioImpl;
import java.util.Calendar;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private ProductoImpl product;
	@Autowired
	private CategoriaImpl categ;
	@Autowired
	private RolImpl rol;
	@Autowired
	private TrabajadorImpl traba;
	@Autowired
	private TipoComproImpl tc;
	@Autowired
	private TipoPagoImpl tp;
	@Autowired
	private DetalleImpl detalle;
	@Autowired
	private ComprobanteImpl compro;
	@Autowired
	private UsuarioImpl user;
	@Autowired
	private NickImpl nick;
	@Autowired
	private BCryptPasswordEncoder encoder;
	Nick n = new Nick();

	@GetMapping("/delete")
	public String delete() {
		detalle.table_delete();
		detalle.table_reinicio();
		compro.table_delete();
		compro.table_reinicio();
		tp.table_delete();
		tp.table_reinicio();
		tc.table_delete();
		tc.table_reinicio();
		product.table_delete();
		product.table_reinicio();
		categ.table_delete();
		categ.table_reinicio();
		user.table_delete();
		user.table_reinicio();
		traba.table_delete();
		traba.table_reinicio();
		rol.table_delete();
		rol.table_reinicio();
		return "redirect:/";
	}

	@GetMapping("/prueba")
	public String create() {
		rol.table_prueba();
		traba.table_prueba();
		user.table_prueba();
		categ.table_prueba();
		product.table_prueba();
		tc.table_prueba();
		tp.table_prueba();
		compro.table_prueba();
		detalle.table_prueba();
		return "redirect:/";
	}

	@GetMapping("")
	public String login() {
		nick.table_delete();
		return "login";
	}

	

    @ModelAttribute //me permite añadir valores al model combirtiendolo en un atributo
	@GetMapping("/dashboard")
	public String dash(Model model) {
    	model.addAttribute("activo", "dashboard"); //me servirá para la navegación 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			n.setUser(user.buscarxnombre(auth.getName()));
			n.setId_nick(1);
			nick.registrar(n);
		}
		Nick temp = new Nick();
		temp = nick.ListarId(1);
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "Dashboard";
	}


	@GetMapping("/perfil")
	public String perfil(Model model) {
    	model.addAttribute("activo", "perfil");
    	n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "usuario/Perfil";
	}

	@GetMapping("/cambio_pass")
	public String cambio_pass(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("activo", "perfil");
		model.addAttribute("nick", n);
		return "usuario/Password";
	}

	@RequestMapping(value = "/actualizar_contra", method = RequestMethod.POST)
	public String actualizar_contra(
			@RequestParam("actual") String actual,
			@RequestParam("nueva1") String nueva1,
			@RequestParam("nueva2") String nueva2,
			Model model) {
		Usuario u = new Usuario();
		if (nueva1.equalsIgnoreCase(nueva2)) {
			if (encoder.matches(actual, n.getUser().getPassword())) {
				u = n.getUser();
				u.setPassword(encoder.encode(nueva1));
				user.modificar(u);
			}
		}
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "redirect:/perfil";
	}
	
	@GetMapping("/graw")
    public String getPieChart(Model model) {
		model.addAttribute("activo", "reportes");
		List<Comprobante> c = compro.listar();
        Map<String, Integer> graphData = new TreeMap<>();
        n = nick.ListarId(1);        
        for(int i = 0; i<c.size();i++) {
        	graphData.put(c.get(i).getFechaEmi_comp().getDate()+ "/"+(c.get(i).getFechaEmi_comp().getMonth()+1) + "/22", ((int)Math.round(c.get(i).getMontoTotal_comp())) );
        }
        model.addAttribute("chartData", graphData);
        
        Map<String, Integer> graphData2 = new TreeMap<>();
        List<Usuario> u = user.listar();
        for(int i =0; i < u.size(); i++){
            int id_usu = u.get(i).getId_user();
            String NomTra = u.get(i).dni_traba.nombre_traba;
            
            List<Comprobante> com = compro.CantidadVenta(id_usu);
            graphData2.put(NomTra,com.size());
            
        }
        
        
        model.addAttribute("chartData2", graphData2);
        model.addAttribute("nick", n);
        return "report"; 
    }
	
	@GetMapping("/busquedas")
    public String busqueda(Model model) {
		model.addAttribute("activo", "busquedas");
        n = nick.ListarId(1);        
        
        model.addAttribute("nick", n);
        return "busqueda"; 
    }
    
    @GetMapping("/busquedas/productos")
    public String busqProductos(Model model){
        List<Producto> productos = product.listar();
        model.addAttribute("productos", productos);
        
        List<Categoria> categorias = categ.listar();
        model.addAttribute("categorias", categorias);
        
        model.addAttribute("inputNom", "");
        model.addAttribute("inputCat", "");
        
        //Nick :v
        n = nick.ListarId(1);
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqProductos";
    }
    @PostMapping("/busqueda/productos")
    public String bProductos(Model model, @RequestParam("nombre") String nombre, @RequestParam("categoria") String cate){
        List<Producto> productos = product.ReportProduct(nombre,cate);
        model.addAttribute("productos", productos);
        
        List<Categoria> categorias = categ.listar();
        model.addAttribute("categorias", categorias);
        
        model.addAttribute("inputNom", nombre);
        model.addAttribute("inputCat", cate);
        
        //Nick :v
        n = nick.ListarId(1);
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqProductos";
    }
    
    @GetMapping("/busquedas/comprobantes")
    public String busqComprobantes(Model model){
        List<Comprobante> comprobantes = compro.listar();
        model.addAttribute("comprobantes",comprobantes);
        
        model.addAttribute("inputCli", "");
        
        //Nick :v
        n = nick.ListarId(1); 
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqComprobantes";
    }
    @PostMapping("/busqueda/comprobantes")
    public String bComprobantes(Model model, @RequestParam("cliente") String cliente){
        List<Comprobante> comprobantes = compro.ReportComprobante(cliente);
        model.addAttribute("comprobantes",comprobantes);
        
        model.addAttribute("inputCli", cliente);
        
        //Nick :v
        n = nick.ListarId(1);
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqComprobantes";
    }
    
    @GetMapping("/busquedas/trabajadores")
    public String busqTrabajadores(Model model){
        List<Trabajador> trabajadores = traba.listar();
        model.addAttribute("trabajadores",trabajadores);
        
        model.addAttribute("inputNom", "");
        
        //Nick :v
        n = nick.ListarId(1); 
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqTrabajadores";
    }
    @PostMapping("/busqueda/trabajadores")
    public String bTrabajadores(Model model, @RequestParam("nombre") String nombre){
        List<Trabajador> trabajadores = traba.ReportTrabajador(nombre);
        model.addAttribute("trabajadores",trabajadores);
        
        model.addAttribute("inputNom", nombre);
        
        //Nick :v
        n = nick.ListarId(1);
        model.addAttribute("nick", n);
        model.addAttribute("activo", "busquedas");
        //Nick :v
        return "busqTrabajadores";
    }
    

	/**************************************************/

	@GetMapping("/1")
	public String ver1() {
		return "Producto/FormCategoria";
	}

	@GetMapping("/2")
	public String ver2() {
		return "Producto/FormProducto";
	}

	@GetMapping("/3")
	public String ver3() {
		return "Producto/ListaCategoria";
	}

	@GetMapping("/4")
	public String ver4() {
		return "Producto/ListaProducto";
	}

	@GetMapping("/5")
	public String ver5() {
		return "Producto/ModiCategoria";
	}

	@GetMapping("/6")
	public String ver6() {
		return "Producto/ModiProducto";
	}

	@GetMapping("/7")
	public String ver7() {
		return "comprobante/ListaComprobante";
	}

	@GetMapping("/8")
	public String ver8() {
		return "comprobante/ListaDetalle";
	}

	@GetMapping("/9")
	public String ver9() {
		return "usuario/ListaRol";
	}

	@GetMapping("/10")
	public String ver10() {
		return "usuario/ListaTrabajador";
	}

	@GetMapping("/11")
	public String ver11() {
		return "usuario/ListaUsuario";
	}

	@GetMapping("/12")
	public String ver12() {
		return "Usuario/ModiRol";
	}

	@GetMapping("/13")
	public String ver13() {
		return "Usuario/ModiTrabajador";
	}

	@GetMapping("/14")
	public String ver14() {
		return "Usuario/ModiUsuario";
	}

	@GetMapping("/15")
	public String ver15() {
		return "Usuario/FormRol";
	}

	@GetMapping("/16")
	public String ver16() {
		return "Usuario/FormTrabajador";
	}

	@GetMapping("/17")
	public String ver17() {
		return "Usuario/FormUsuario";
	}

	@GetMapping("/18")
	public String ver18() {
		return "usuario/Perfil";
	}

	@GetMapping("/19")
	public String ver19() {
		return "usuario/Password";
	}

	@GetMapping("/20")
	public String ver20() {
		return "comprobante/ListaProducto_Compro";
	}

	@GetMapping("/21")
	public String ver21() {
		return "comprobante/ListaDetalle";
	}

	@GetMapping("/22")
	public String ver22() {
		return "comprobante/FormComprobante";
	}
}
