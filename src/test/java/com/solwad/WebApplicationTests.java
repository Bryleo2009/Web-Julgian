package com.solwad;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

import com.solwad.model.Rol;
import com.solwad.model.Usuario;
import com.solwad.repo.IRolRepo;
import com.solwad.repo.IUsuarioRepo;
import com.solwad.service.impl.ProductoImpl;



@SpringBootTest
class WebApplicationTests {

	@Autowired
	private IUsuarioRepo repo;
	@Autowired
	private IRolRepo role;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Test
	public void crearuser() {
		String cadena = " Esta es una cadena de prueba";
		cadena = cadena.trim();
		String[] nueva = cadena.split("\\s+");
		System.out.println(cadena);
		//assertTrue(cadena.equalsIgnoreCase("Esta es una cadena de prueba"));
		assertTrue(nueva[0].equalsIgnoreCase("Esta"));
	}

}
