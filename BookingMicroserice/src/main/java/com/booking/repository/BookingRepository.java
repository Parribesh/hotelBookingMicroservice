package com.booking.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	public List<Booking> findByCheckInDateAndHotelIdAndHotelRoomId(Date checkInDate, int hotelId, int hotelRoomId );

	public List<Booking> findByCustomerMobile(String phoneNumber);

	public List<Booking> findByEmail(String email);
}
