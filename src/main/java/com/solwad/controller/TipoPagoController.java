package com.solwad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.solwad.model.Nick;
import com.solwad.service.ITipoPagoService;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.TipoPagoImpl;

@Controller
public class TipoPagoController {
	@Autowired
	private TipoPagoImpl service;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	//
	public String list_tp(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "";
	}
}
