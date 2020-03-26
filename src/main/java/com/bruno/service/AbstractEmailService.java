package com.bruno.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bruno.domain.Cliente;
import com.bruno.domain.Pedido;


public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
	
	@Override
	public void sendOrderConfigurationEmail(Pedido pedido) {
		SimpleMailMessage sm =prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido Confirmado : Código :" + pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		
		return sm;
	}
	//Montando o template setado com o objeto pedido
	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);

		return templateEngine.process("email/confirmacaoPedido", context);

	}
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
	
		SimpleMailMessage sm =prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitaçao de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha :" + newPass);
		
		return sm;
	}
	
//	@Override
//	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
//
//		try {
//
//			MimeMessage sm = prepareMineMessageFromPedido(pedido);
//			sendHtmlEmail(sm);
//		} catch (MessagingException e) {
//			sendOrderConfigurationEmail(pedido);
//		}
//	}

//	protected MimeMessage prepareMineMessageFromPedido(Pedido pedido) throws MessagingException {
//		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
//		mmh.setTo(pedido.getCliente().getEmail());
//		mmh.setFrom(sender);
//		mmh.setSubject("Pedido confirmado!  Código :" + pedido.getId());
//		mmh.setSentDate(new Date(System.currentTimeMillis()));
//		mmh.setText(htmlFromTemplatePedido(pedido), true);
//
//		return mimeMessage;
//	}

}
