package com.ricardo.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


import com.ricardo.cursomc.domain.Cliente;
import com.ricardo.cursomc.domain.Pedido;

@Service
public interface EmailService {

	// operações que o serviço de e-mail deve oferecer
	
	//está operação, vai utilizar a operacção de enviar e-mail, ou seja: sendEmail(SimpleMailMessage msg)
	// antes de enviar o e-mail, tempos que preparar o SimpleMailMessage, em nosso caso, a partir de uma pedido.
	// pra fazer isso utilizaremos o padrão de projeto template method.
	// iremos instanciar o SimpleMailMessage  a partir de um pedido, preparamos o prepareSimpleMailMessageFromPedido
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);
		
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage msg);
	
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
