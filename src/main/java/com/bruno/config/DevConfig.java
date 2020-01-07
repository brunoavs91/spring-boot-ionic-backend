package com.bruno.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bruno.service.DBService;
import com.bruno.service.EmailService;
import com.bruno.service.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public Boolean instantiateDataBase() throws Exception {
		
		if("create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDataBase();
		return true;
	}
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
