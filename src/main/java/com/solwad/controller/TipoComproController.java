package com.solwad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.solwad.model.Nick;
import com.solwad.service.ITipoComproService;
import com.solwad.service.impl.NickImpl;
import com.solwad.service.impl.TipoComproImpl;

@Controller
public class TipoComproController {
	@Autowired
	private TipoComproImpl service;
	@Autowired
	private NickImpl nick;
	Nick n = new Nick();

	//
	public String list_tc(Model model) {
		n = nick.ListarId(1);
		model.addAttribute("nick", n);
		return "";
	}

}
