package com.ricardo.cursomc.services;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.domain.Pedido;

@Service
public interface EmailService {

	// operações que o serviço de e-mail deve oferecer
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
