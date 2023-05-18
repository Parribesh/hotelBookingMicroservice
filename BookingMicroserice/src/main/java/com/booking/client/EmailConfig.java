package com.booking.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig {
	
	@Bean
	public SimpleMailMessage emailTemplate() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("paribeshtraining77@gmail.com");
		message.setFrom("neuppari@gmail.com");
		message.setSubject("Invoice");
	    message.setText("");
	    return message;
	}
}
