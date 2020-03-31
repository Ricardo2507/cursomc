package com.ricardo.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class EmailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@NotEmpty(message= "E-mail  do cliente é de preenchimento orbrigatório")
	@Email(message="E-mail inválido. Corrija-o e tente novamente")
	private String email;
	
	public EmailDTO() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
