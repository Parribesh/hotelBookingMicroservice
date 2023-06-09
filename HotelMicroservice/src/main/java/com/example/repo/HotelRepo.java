package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.domain.Hotel;

@Repository
public interface HotelRepo extends JpaRepository<Hotel, Integer> {

	public List<Hotel> findHotelByHotelNameLikeOrCityLikeOrStateLike(String string, String string2, String string3);
}
