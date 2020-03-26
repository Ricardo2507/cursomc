package com.ricardo.cursomc.services;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.stereotype.Service;


import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.domain.Pedido;

@Service
public interface EmailService {

	void sandOrderConfirmationEmail(Pedido pedido);
	
	void sandEmail(SimpleMessage email);
	
	void sandNewPasswordEmail(Cliente cliente, String newPass);
}
