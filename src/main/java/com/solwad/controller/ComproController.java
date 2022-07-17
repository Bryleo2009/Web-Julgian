package com.solwad.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.solwad.model.Comprobante;
import com.solwad.model.Detalle;
import com.solwad.model.Nick;
import com.solwad.model.Producto;
import com.solwad.model.TipoCompro;
import com.solwad.model.TipoPago;
import com.solwad.model.Usuario;
import com.solwad.service.IComprobanteService;
import com.solwad.service.impl.ComprobanteImpl;
import com.solwad.service.impl.DetalleImpl;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.ProductoImpl;
import com.solwad.service.impl.TipoComproImpl;
import com.solwad.service.impl.TipoPagoImpl;
import java.util.ArrayList;

@Controller
@RequestMapping("/comprobantes")
public class ComproController {
	@Autowired
	private ComprobanteImpl service;
	@Autowired
	private ProductoImpl product;
	List<Comprobante> comprobantes = null;
	List<Producto> productos = null;
	
	@Autowired
	private DetalleImpl service_Det;
        List<Detalle> detalles = null;
        //
        @Autowired
	private TipoComproImpl service_TC;
        List<TipoCompro> tipoCom = null;
	//
        @Autowired
	private TipoPagoImpl service_TP;
        List<TipoPago> tipoPago = null;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("activo", "comprobantes");
	}

	//
	@GetMapping("")
	public String list_compro(Model model) {
		comprobantes = service.listar();
		n = nick.ListarId(1);
		model.addAttribute("comprobantes", comprobantes);
		model.addAttribute("nick", n);
		return "comprobante/ListaComprobante";
	}

	
	
	@GetMapping("/new_compro")
	public String new_compro(Model model) {
		String serie = "";
		int num = 0;
		comprobantes = service.listar();
		for(int i = comprobantes.size()-1; i<comprobantes.size();i++) {
			serie = comprobantes.get(i).getId_comp();
			num = Integer.parseInt(serie.substring(1,serie.length()));
		}
		
		if(num>=0 && num <=9) {serie = "N0000"+(num+1);}
		else if (num>=10 && num <=99) {serie = "N000"+(num+1);}
		else if (num>=100 && num <=999) {serie = "N00"+(num+1);}
		else if (num>=1000 && num <=9999) {serie ="N0"+(num+1);}
		else {serie = "N"+num+1;}
		
		
		double subtotal = 0, igv, total,sb;
        detalles = service_Det.listDetalle();
        
        
        for(int i=0;i<detalles.size();i++){
            sb = detalles.get(i).precioTotal_detalle;
            subtotal = subtotal + sb;
        }
        igv = subtotal*0.18;
        total = subtotal + igv;
        
        tipoCom = service_TC.listar();
        tipoPago = service_TP.listar();
        
        model.addAttribute("num",serie);
        
        model.addAttribute("Subtotal", subtotal);
        model.addAttribute("igv", igv);
        model.addAttribute("Total", total);
        
        model.addAttribute("tipoCom",tipoCom);
        model.addAttribute("tipoPago",tipoPago);
        
        n = nick.ListarId(1);
        model.addAttribute("detalles",detalles);
		model.addAttribute("nick", n);
		return "comprobante/FormComprobante";
	}
	
	@GetMapping("/select_product")
	public String select_product(Model model) {
		productos = product.listar();
		n = nick.ListarId(1);
		model.addAttribute("productos", productos);
		model.addAttribute("nick", n);
		System.out.print(n.user.dni_traba.nombre_traba);
		return "comprobante/ListaProducto_Compro";
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String reg_compro (@RequestParam("tipoComp") TipoCompro tipoComp,
                                  @RequestParam("numSerie") String numSerie ,
                                  @RequestParam("fechaIng") Date fechaIng ,
                                  @RequestParam("nomCliente") String nomCliente,
                                  @RequestParam("ideCliente") String ideCliente ,
                                  @RequestParam("nomTraba") Usuario nomTraba ,
                                  @RequestParam("tipoPago") TipoPago tipoPago ,
                                  @RequestParam("SubTotal") double SubTotal,
                                  @RequestParam("IGV") double IGV,
                                  @RequestParam("Total") double Total,Model model) {
                //agregar comprobante y cambiar idcomp de detalle
                //obtener ultimo id de comprobantes
                //cambiar id_comp detalle por ultimo id_comp+1
                //listar
				Comprobante c = new Comprobante();
				c.setFechaEmi_comp(fechaIng);
				c.setId_comp(numSerie);
				c.setId_tc(tipoComp);
				c.setId_tp(tipoPago);
				c.setId_user(nomTraba);
				c.setIdentClient_comp(ideCliente);
				c.setMontoSubtotal_comp(Math.round(SubTotal*100.0)/100.0);
				c.setMontoTotal_comp(Math.round(Total*100.0)/100.0);
				c.setNomClient_comp(nomCliente);
				service.registrar(c);				
                comprobantes = service.listar();
                
                detalles = service_Det.listDetalle();
                for(int i=0; i<detalles.size();i++) {
                	Detalle d = detalles.get(i);
                	d.setId_comp(c);
                	service_Det.modificar(d);                	
                }
                
		model.addAttribute("comprobantes",comprobantes);
		n = nick.ListarId(1);
		model.addAttribute("nick",n);
		return "redirect:/comprobantes";
	}

	//
        @GetMapping("/eliminar_compro")
	public String elim_compro(Model model,
        @RequestParam("id_comp") String id_comp) {
		detalles = service_Det.listar();
                System.out.println(detalles);
                System.out.println(detalles.size());
		for(int i=0;i<service_Det.listar().size();i++) {
			if(detalles.get(i).getId_comp().getId_comp().equalsIgnoreCase(id_comp)) {
                            System.out.println(detalles.get(i).id_dcomp);
                            service_Det.eliminar(detalles.get(i).id_dcomp);
			}
		}
                service.eliminar(id_comp);
                
                comprobantes = service.listar();
                n = nick.ListarId(1);
            
		model.addAttribute("nick", n);
		model.addAttribute("comprobantes",comprobantes);
		return "redirect:/comprobantes";
	}

	//
	@GetMapping("/detalle_compro")
	public String deta_compro(@RequestParam(value = "id_comp") String id_comp,Model model) {
		Comprobante comprobante = service.ListarId(id_comp);
                List<Detalle>tempo = new ArrayList<>();
		
		detalles = service_Det.listar();
		for(int i=0;i<service_Det.listar().size();i++) {
			if(detalles.get(i).getId_comp().getId_comp().equalsIgnoreCase(id_comp)) {
				tempo.add(detalles.get(i));
				//System.out.println(tempo);
			}
		}
                double igv=comprobante.getMontoSubtotal_comp()*.18;
                igv = Math.round(igv*100.0)/100.0;
                
		n = nick.ListarId(1);
		model.addAttribute("detalles",tempo);
		model.addAttribute("comprobante",comprobante);
		model.addAttribute("nick", n);
                model.addAttribute("igv", igv);
		return "comprobante/ModiComprobante";
	}

	//
	public String act_compro(Model model) {
                n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "";
	}

}
