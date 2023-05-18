package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Hotel;
import com.example.domain.Rating;

@Service
public interface IHotelService {
	
	
	public String createHotel(Hotel h);
	public List<Hotel> getHotelBySearchTerms(String searchText);
	public Hotel getHotelById(int hotelId);
	public Hotel getRoomByType(String type);
	public Rating addAReview(int hotelId, Rating r) throws Exception;
}
