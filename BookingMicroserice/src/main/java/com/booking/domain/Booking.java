package com.booking.domain;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Booking {
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	private int hotelId; //communicates with hotel management to fetch hotel details
	private int hotelRoomId;	
	private int noRooms;
	private Date checkInDate;
	private Date checkOutDate;
	private Date bookedOnDate;
	private String status; //cancelled, completed (can be simply compared), upcoming
	private float price;
	private float discount;	
	private String customerMobile; //identify the customer who booked
	private String roomType;
	@ManyToMany(cascade = CascadeType.ALL)	
	private Set<Guest> guests;
	
	private String email;
	
	//@Transient
	//private String hotelName;	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//public String getHotelName() {
	//	return hotelName;
	//}
	//public void setHotelName(String hotelName) {
	//	this.hotelName = hotelName;
	//}
	public String getRoomType() {
		return roomType;
	}
	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Booking(int bookingId, int hotelId, int hotelRoomId, int noRooms, Set<Guest> guests, Date checkInDate,
			Date checkOutDate, Date bookedOnDate, String status, float price, float discount,
			String customerMobile, String roomType) {
		super();
		this.bookingId = bookingId;
		this.hotelId = hotelId;
		this.hotelRoomId = hotelRoomId;
		this.noRooms = noRooms;
		this.guests = guests;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookedOnDate = bookedOnDate;
		this.status = status;
		this.price = price;
		this.discount = discount;
		this.customerMobile = customerMobile;
		this.roomType = roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public int getNoRooms() {
		return noRooms;
	}
	public void setNoRooms(int noRooms) {
		this.noRooms = noRooms;
	}
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public int getHotelRoomId() {
		return hotelRoomId;
	}
	public void setHotelRoomId(int hotelRoomId) {
		this.hotelRoomId = hotelRoomId;
	}
	public Set<Guest> getGuests() {
		return guests;
	}
	public void setGuests(Set<Guest> guests) {
		this.guests = guests;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Date getBookedOnDate() {
		return bookedOnDate;
	}
	public void setBookedOnDate(Date bookedOnDate) {
		this.bookedOnDate = bookedOnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
}
