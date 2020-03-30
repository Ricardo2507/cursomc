package com.ricardo.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ricardo.cursomc.services.DBService;
import com.ricardo.cursomc.services.EmailService;
import com.ricardo.cursomc.services.MockEmailService;


@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantuateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		
		return true;
	}
	
	// quando criamos um método com a anotação @Bean, ele fica 
	// disponível como um componente de nosso sistema
	
	@Bean
	public EmailService emailService() {
		
		return new MockEmailService();
	}

	

}
