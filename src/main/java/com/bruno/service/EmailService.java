package com.bruno.service;

import org.springframework.mail.SimpleMailMessage;

import com.bruno.domain.Pedido;

public interface EmailService {
	
	/**
	 * 
	 * @param pedido
	 */
	void sendOrderConfigurationEmail(Pedido pedido);
	
	/**
	 * Enviar mensagem
	 * @param msg
	 */
	void sendEmail(SimpleMailMessage msg);

}
