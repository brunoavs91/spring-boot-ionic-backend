package com.bruno.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bruno.domain.Cliente;
import com.bruno.repository.ClienteRepository;
import com.bruno.service.exception.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private Random random = new Random();
	
	@Autowired
	private EmailService emailService;
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			
			throw new ObjectNotFoundException("Email não encontrado");
			
		}
		String newPass = newPassWord();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}

	private String newPassWord() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		// gera um digito baseado no unicode
		if (opt == 0) {
			// gerar um numero aletorio de 0 a 9 e somar com 48 codigo do 0
			return (char) (random.nextInt(10) + 48);
		}
		// gera letra maiuscula
		else if (opt == 1) {
			return (char) (random.nextInt(26) + 65);

		} else {// letra minuscula
			return (char) (random.nextInt(26) + 97);
		}

	}
}
