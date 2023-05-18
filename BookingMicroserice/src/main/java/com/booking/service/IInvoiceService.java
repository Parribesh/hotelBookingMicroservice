package com.booking.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.domain.Booking;
import com.booking.util.CreatePdf;

@Service
public class IInvoiceService implements InvoiceService {
	
	public static final String DEST = "invoices/invoice.pdf";
	public static final String SUBJECT = "Invoice";
	public static final String BODY = "Here is your Invoce! Thanks for your Business.";
	
	@Autowired EmailService emailService;
	
	@Override
	public void createInvoice(Booking b, Object hotel) throws IOException {
		File file = new File(DEST);
		file.getParentFile().mkdir();
		new CreatePdf().createPdf(DEST, b, hotel);
		
		emailService.sendMailWithAttachment(b.getEmail(), SUBJECT,BODY, DEST);
//		emailService.sendPreConfiguredMail("We appreciate your business!");
	}

}
