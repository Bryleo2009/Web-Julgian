package com.solwad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.solwad.service.impl.UsuarioImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UsuarioImpl usera;
	
	@Autowired
	private BCryptPasswordEncoder pass;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() { 
		BCryptPasswordEncoder bCryptPassiordncodsr = new BCryptPasswordEncoder();
		return bCryptPassiordncodsr;

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
	throws Exception {
		auth.userDetailsService(usera).passwordEncoder(pass);

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http .csrf().disable()
	.authorizeRequests()
	.antMatchers("/","/auth/**","/public/**","/css/**","/js/**","/img/**","/prueba","/delete","/recursos/**","/ruta/**").permitAll()
	.antMatchers("/productos/**")/*.hasRole("GERENTE")*/.authenticated()
	.antMatchers("/usuarios/**").hasRole("GERENTE")
	.antMatchers("/roles/**").hasRole("GERENTE")
	.antMatchers("/trabajadores/**").hasRole("GERENTE")
	.antMatchers("/comprobantes/**").authenticated()
	.antMatchers("/perfil/**").authenticated()
	.antMatchers("/actualizar_contra/**").authenticated()
	.antMatchers("/cambio_pass/**").authenticated()
	.antMatchers("/dashboard/**").authenticated()
    .and()
    .formLogin().loginPage("/").defaultSuccessUrl("/dashboard",true).failureUrl("/?error=true")
    .permitAll()
    .and()
    .logout()
    .permitAll();
		/*
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();*/
	}
}
