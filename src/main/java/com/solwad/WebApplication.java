package com.solwad;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

	private static void carpetas() {
		String crear = "C:\\solwad\\recursos";
		File c = new File(crear);
		if(!c.exists()) {
			if(c.mkdirs()) {
				System.out.println("Carpeta creada");
			}else {
				System.out.println("Carpeta no creada");
			}
		}
		
	}
	
	public static void main(String[] args) {
		carpetas();
		SpringApplication.run(WebApplication.class, args);
	}

}
