package com.booking.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.booking.domain.Booking;

@Service
public interface IBookingService {
	public Booking createBooking(Booking b);
	public Booking getBookingById(int bookingId);
	public void deleteBooking(Booking b);
	public List<Booking> findAll();
	List<Booking> findBookingByCheckInDateAndHotelIdAndHotelRoomId(Date checkIn, int hotelId, int hotelRoomId);
	public Object getHotelById(int hotelId);
	List<Booking> getBookingByPhone(String phoneNumber);
	String cancelBookingById(int bookingId);
	Booking updateStatus(Booking b);
}
