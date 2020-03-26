package com.bruno.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.bruno.domain.Cliente;
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
	
//	/**
//	 * Confirmar Email HTML
//	 * @param obj
//	 */
//	void sendOrderConfirmationHtmlEmail(Pedido obj);

	/**
	 * enviar email html
	 * @param msg
	 */
	void sendHtmlEmail(MimeMessage msg);
	
	/**
	 * Enviar novo password 
	 * @param cliente
	 * @param newPass
	 */
	void sendNewPasswordEmail(Cliente cliente, String newPass);

}
