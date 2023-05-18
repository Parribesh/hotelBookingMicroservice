package com.booking.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.booking.domain.Booking;

@Service
public interface InvoiceService {
	public void createInvoice(Booking b, Object hotel) throws IOException;
}
