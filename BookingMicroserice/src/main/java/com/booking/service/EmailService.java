package com.booking.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage preConfiguredMessage;
	
	public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach ){
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				mimeMessage.setFrom(new InternetAddress("neuppari@gmail.com"));
				mimeMessage.setSubject(subject);
				mimeMessage.setText(body);
				
				FileSystemResource file = new FileSystemResource(new File("invoices/invoice.pdf"));
				System.out.println(file.getFilename());
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.addAttachment(file.getFilename(), file, "application/pdf");
				helper.setText("Here is Your Invoice For the Booking!");
				
			}
			
		};
		
		try {
			mailSender.send(preparator);
		} catch(MailException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	 public void sendPreConfiguredMail(String message)
	    {
	        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
	        mailMessage.setText(message);
	        mailSender.send(mailMessage);
	    }
}
