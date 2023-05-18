package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.client.BookingClient;
import com.example.domain.Hotel;
import com.example.domain.Rating;
import com.example.repo.HotelRepo;

@Service
public class HotelService implements IHotelService{
	
	@Autowired HotelRepo hotelRepo;
	@Autowired BookingClient bookingClient;
	@Override
	public String createHotel(Hotel h) {
		hotelRepo.save(h);
		return "success";
	}

	@Override
	public List<Hotel> getHotelBySearchTerms(String searchText) {
		
		return hotelRepo.findHotelByHotelNameLikeOrCityLikeOrStateLike("%"+searchText+"%", "%"+searchText+"%", "%"+searchText+"%");
	}

	@Override
	public Hotel getHotelById(int hotelId) {
		return hotelRepo.findById(hotelId).orElse(null);
	}

	@Override
	public Hotel getRoomByType(String type) {
		
		return null;
	}


	@Override
	public Rating addAReview(int hotelId, Rating r) throws Exception {
		Hotel h = hotelRepo.findById(hotelId).orElse(null);
		if(bookingClient.getBookingById(hotelId) != null) {
			 Set<Rating> rList = h.getRatings();
			 rList.add(r);
			 h.setRatings(rList);
			hotelRepo.save(h);
		}else {
			throw new Exception();
		}
		
		return r;
	}

}
