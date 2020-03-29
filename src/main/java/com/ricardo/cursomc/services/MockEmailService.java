package com.ricardo.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.ricardo.cursomc.domain.Cliente;

public class MockEmailService extends AbstractEmailService {
	
	// para mostrar no Log do servidor
	//  logger referente a esta classe
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

	// o método abaixo pertence à interface
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
		
	}

	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail HTML...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado");
		
	}

}
