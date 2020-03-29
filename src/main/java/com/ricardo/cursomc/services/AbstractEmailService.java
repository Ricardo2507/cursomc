package com.ricardo.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.ricardo.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	
	// e-mail padrão definido no aplication.properties
	// @Value puxa o valor que está em aplication.properties
	
	@Value("${default.sender}")
	private String sender;
	
	// para enviar e-mail para o fornecedor, a fim de preparar o pedido
	
	@Value("${default.recipient}")
	private String recebePedido;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
		
	}
	
	
    // protected pois pode ser acessado por subclasses
	// não pode ser acessado por controladores e serviços
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail(), recebePedido);
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + pedido.getId());
		// garante data com horário do meu servidor.
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}

}
