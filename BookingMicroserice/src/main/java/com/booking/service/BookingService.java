package com.booking.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.client.HotelClient;
import com.booking.domain.Booking;
import com.booking.repository.BookingRepository;

@Service
public class BookingService implements IBookingService {
	
	@Autowired
	BookingRepository bookingRepo;
	@Autowired HotelClient hotelClient;
	
	@Override
	public Booking createBooking(Booking b) {

		return bookingRepo.save(b);
	}

	@Override
	public Booking getBookingById(int bookingId) {
		
		return bookingRepo.findById(bookingId).orElse(null);
	}

	@Override
	public void deleteBooking(Booking b) {
		bookingRepo.delete(b);

	}
	@Override
	public List<Booking> findBookingByCheckInDateAndHotelIdAndHotelRoomId(Date checkIn, int hotelId, int hotelRoomId ){
		
		return bookingRepo.findByCheckInDateAndHotelIdAndHotelRoomId(checkIn, hotelId, hotelRoomId);
	}

	@Override
	public List<Booking> findAll() {
		
		return bookingRepo.findAll();
	}

	@Override
	public Object getHotelById(int hotelId) {
		
		return hotelClient.getHotelById(hotelId);
	}
	
	@Override
	public List<Booking> getBookingByPhone(String phoneNumber) {
	
		return bookingRepo.findByCustomerMobile(phoneNumber);
	}
	
	@Override
	public String cancelBookingById(int bookingId) {
		Booking b = bookingRepo.findById(bookingId).orElse(null);
		if(b != null) {
			b.setStatus("Canceled");
			bookingRepo.save(b);
			return "success";
		}
		return "error";
	}

	public List<Booking> getBookingByEmail(String email) {
		return bookingRepo.findByEmail(email);
	}

	@Override
	public Booking updateStatus(Booking b) {
		LocalDate date = LocalDate.now();
		LocalDate checkInDate = b.getCheckInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate checkOutDate = b.getCheckOutDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		boolean isAfter =  date.isAfter(checkInDate);
		boolean isBefore =  date.isBefore(checkOutDate);
		if(isAfter && isBefore) {
			b.setStatus("ongoing");
		}else if( isAfter && isBefore == false) {
			b.setStatus("completed");
		}
		return b;
	}

}
