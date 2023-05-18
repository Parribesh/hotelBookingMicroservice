<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<head>
<meta charset="ISO-8859-1">
<title>Home Page of Travel Gig</title>
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="./js/hotelScript.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/home.css">
</head>
<body class = "w-100">
<div class="header pt-4" style="background-color: #caddfa; font-weight: 300">
<span class='' style="font-size: 2rem; display: block">Welcome to Travel Gig</span>
<span style="font-size: 1.2rem" class="text-muted">Search your desired hotel</span>
<h3 class='d-none'><security:authorize access="isAuthenticated()">
    authenticated as <span id="username" ><security:authentication property="principal.username"/> </span> 
</security:authorize>
</h3>
<div class="w-100 text-right">
	<button type = "button" id="getMyBookings" class="btn mb-3">Bookings</button>
	<a style ="color: white; text-decoration: none" href="/profile"><button class="btn mb-3" >Profile</button></a>
	<a style ="color: white; text-decoration: none" href="/register"><button class="btn mb-3" >Join Us</button></a>
</div>
</div>

<div class="search-option border rounded" style="padding:50px;margin-top:50px;margin-bottom:50px">
	<div class="d-flex align-items-center ">
		<span class="m-0" style="padding: 0; font-size: 2rem" >Narrow your search results</span>
	</div>
	<div class="options d-flex form-row">
		<div class="col-3">
			Hotel/City/State/Address <input class="form-control" type="text" id="searchLocation" name="searchLocation"/>
		</div>
		<div class="col">
			No. Rooms: <input class="form-control" type="number" id="noRooms" name="noRooms"/>
		</div>
		<div class="col">
			No. Guests: <input class="form-control" type="number" id="noGuests" name="noGuests"/>
		</div>
		<div class="col-2">
		Check-In Date: <input class="form-control" type="date" id="checkInDate" name="checkInDate"/>
		</div>
		<div class="col-2">
		Check-Out Date: <input class="form-control" type="date" id="checkOutDate" name="checkOutDate"/>
		</div>
		<div class="col d-flex align-items-end justify-content-end">
		<input class="searchBtn btn" type="button" id="searchBtn" value="SEARCH"/>
		</div>
	</div>
</div>

<div class="row w-100">
<div class="filter col-2 border rounded" style="margin-left:50px;padding:25px">
	
	<br>	
	<!--  Star Rating: 
	<select class="form-control" id="filter_starRating">
		<option value=0>Select</option>
		<option value=1>1</option>
		<option value=2>2</option>
		<option value=3>3</option>
		<option value=4>4</option>
		<option value=5>5</option>
	</select><br>--> 
	
	Star Rating:<br>
	<div class="form-check-inline">
		<label class="form-check-label">
			<input type="checkbox" class="star_rating form-check-input" id="1_star_rating" value=1>1
		</label>
	</div>
	<div class="form-check-inline">
		<label class="form-check-label">
			<input type="checkbox" class="star_rating form-check-input" id="2_star_rating" value=2>2		
		</label>
	</div>
	<div class="form-check-inline">
		<label class="form-check-label">
			<input type="checkbox" class="star_rating form-check-input" id="3_star_rating" value=3>3
		</label>
	</div>
	<div class="form-check-inline">
		<label class="form-check-label">
			<input type="checkbox" class="star_rating form-check-input" id="4_star_rating" value=4>4
		</label>
	</div>
	<div class="form-check-inline">
		<label class="form-check-label">
			<input type="checkbox" class="star_rating form-check-input" id="5_star_rating" value=5>5
		</label>
	</div><br><br>
	
	Range:
	<div class="slidecontainer">
  		<input type="range" min="1" max="500" value="500" class="slider" id="priceRange">
  		<p>Price: $<span id="priceValue"></span></p>
	</div>
	
	<div class="form-check">
		<input type="checkbox" class="hotel_amenity form-check-input" id="amenity_parking" value="PARKING"/>
		<label class="form-check-label" for="amenity_parking">Parking</label><br>
		
		<input type="checkbox" class="hotel_amenity form-check-input" id="amenity_checkin_checkout" value="CHECK-IN & CHECK-OUT TIMES"/>
		<label class="form-check-label" for="amenity_checkin_checkout">Check-In & Check-Out Times</label><br>
		
		<input type="checkbox" class="hotel_amenity form-check-input" id="amenity_breakfast" value="BREAKFAST"/>
		<label class="form-check-label" for="amenity_breakfast">Breakfast</label><br>
		
		<input type="checkbox" class="hotel_amenity form-check-input" id="amenity_bar_lounge" value="BAR OR LOUNGE"/>
		<label class="form-check-label" for="amenity_bar_lounge">Bar / Lounge</label><br>
		
		<input type="checkbox" class="hotel_amenity form-check-input" id="amenity_fitness_center" value="FITNESS CENTER"/>
		<label class="form-check-label" for="amenity_fitness_center">Fitness Center</label><br>
	</div>
	
	<input style="margin-top:25px" class="btn btn-primary" type="button" id="filterBtn" value="FILTER"/>	
</div>


<div class="col-7 border rounded" style="margin-left:50px;">
	<div style='text-align:center;font-size:20px;font-family:"Trebuchet MS", Helvetica, sans-serif'>List of Hotels:</div>	
	
	<div id="listHotel">
		<div style='width:100%;' >
		
				<div style="">
					<span> Results: </span>
				</div>
	
			<div  id="tableBody">
				
			</div>
		</div>
	</div>
	
</div>
</div>

<!-- Footer Starts -->

<div class='footer d-flex flex-column w-100 justify-content-center align-items-end'>
	<h3>@HotelGig Inc.</h3>
	<div class="address">
		<span> 123 main St</span>
		<span> City, State- ZipCode</span>
	</div>
</div>

<div class="modal" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Search Hotel Rooms</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">        
        <div class="col">
        	<input class="form-control" type="hidden" id="modal_hotelId"/>
        	Hotel Name: <input readonly="true" class="form-control" type="text" id="modal_hotelName"/>
        	No. Guests: <input class="form-control" type="number" id="modal_noGuests"/>
        	Check-In Date: <input class="form-control" type="date" id="modal_checkInDate"/>
        	Check-Out Date: <input class="form-control" type="date" id="modal_checkOutDate"/>
        	Room Type: 
        	<select class="form-control" id="select_roomTypes">
        	</select>
        	No. Rooms: <input class="form-control" type="number" id="modal_noRooms"/>
        	<input style="margin-top:25px" class="btn btn-searchHotelRooms form-control btn-primary" type="button" id="search_rooms" value="SEARCH"/>       	
        </div>
        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="myBookingsModal">
  <div class=" modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Here Are Your Bookings!</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">        
        <div class="d-flex flex-column align-items-end" id = "myBookingModal_bookings">
        	<div class = "d-flex">
    			<label>Please Enter Your phone Number:</label>
    			 <input id="booking-phone" type= "text" class="form-control"/>
    		</div>
    		<div>
    			<input type = "submit" class="btn btn-primary" id="search-booking" />
    		</div>
    		
    		<div class="w-100 mt-3">
    			<table class='container' style="width: 100%; border:1px solid grey;">
    				<thead >
    					<tr style="margin: 12px; height: 40px; background-color:#caddfa; " class="w-100 text-secondary text-right">
    						<th>BookingId</th>
    						<th> BookingDate</th>
    						<th>CheckInDate</th>
    						<th>CheckOutDate</th>
    						<th>CustomerMobile</th>
    						<th>Price</th>
    						<th>Status</th>
    						<th style="width: 40px; color: red;" colspan="2">Actions</th>
    					</tr>
    				</thead>
    				
    				<tbody id = "booking-show-table">
    				
    				</tbody>
    			</table>
    		</div>
        </div>
    </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="select_room">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Room Details</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      
      <div style="width: 50%; padding: 4px">
		    <span class="text-danger" id= "booking-error"></span>
		</div>
				
			<!-- Modal body -->
      	<div style = "display:flex; justify-content:space-around" class="modal-body" id="hotelRooms_modalBody">        
              	
               	<div>
	               	<h3>Price: $<span class="text-primary" id="room_price">$</span></h3>
	               	<h6><span class="text-danger" id="available_rooms"></span> Rooms available at this price</h6>
	             	<img src="https://thumbs.dreamstime.com/b/hotel-room-beautiful-orange-sofa-included-43642330.jpg" width="200" height="200"/>
             	</div>
             	
             	<div class="d-flex flex-column">
             		<h3>Policies</h3>
		              	<ul class="text-danger" id="policies">
		              	</ul>
		            <h3  >Amenities</h3>
		               	<ul class="d-flex justify-content-between w-100 text-primary" id="amenities">
		              	</ul>
		              	
		              
		             <button id="book_button" class="btn btn-success" >BOOK</button>
              	</div>
            
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="hotelRoomsModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="hotelRooms_modalBody">        
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="reviewModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Reviews!!</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="reviewModal_modalBody">        
         
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="bookingHotelRoomModal">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title"></h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

      <!-- Modal body -->
      <div class="modal-body" id="bookingRoom_modalBody">        
        	<div class="col">
       			<div><input class="form-control" type="hidden" id="booking_hotelId"/></div>
       			<div><input class="form-control" type="hidden" id="booking_hotelRoomId"/></div>
	        	<div>Hotel Name: <input readonly="true" class="form-control" type="text" id="booking_hotelName"/></div>
	        	<div>Customer Email: <input readonly="true" class="form-control" type="email" id="booking_email"/></div>
	        	<div>Customer Mobile: <input class="form-control" type="text" id="booking_customerMobile"/></div>
       			<div id="noGuestsDiv">No. Guests: <input readonly="true" class="form-control" type="number" id="booking_noGuests"/></div>
       			<div>No. Rooms: <input readonly="true" class="form-control" type="number" id="booking_noRooms"/></div>
       			<div>Check-In Date: <input readonly="true" class="form-control" type="text" id="booking_checkInDate"/></div>
       			<div>Check-Out Date: <input readonly="true" class="form-control" type="text" id="booking_checkOutDate"/></div>
       			<div>Room Type: <input readonly="true" class="form-control" type="text" id="booking_roomType"/></div>
       			<div>Discount: $<span id="booking_discount"></span></div>
       			<div>Total Price: $<span id="booking_price"></span></div>       			
       			<div style='margin-top:20px'>
       				<button class='btn-confirm-booking btn'>Confirm</button>
       				<button class='btn '>Edit</button>
       			</div>
        	</div>          
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="guestModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Enter Guest <span id="num_guests">1<span> Info</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body">    
	   <div class="col" id= >
	      <input class="form-control" type="hidden" id="modal_guestId"/>
	      <div class="d-flex w-100 flex-column" id = "modal-guest-form">
	       
	      </div>
	      <input style="margin-top:25px" class="btn btn-addGuests form-control btn-primary" type="button" id="add_guests" value="ADD GUEST"/>
	   </div>
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>


<div class="modal" id="confirmModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Confirmed </h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body">    
	  <div> Your Room Was Successfully Booked!!</div>
	  <div> Enjoy your Stay!!</div>
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="faqModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Confirmed </h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body" id='faqModel_body'> 
		<div class='form-group'>
		  	<label>Have a Question?</label>
		  	<textarea id='question_text' class='form-control' rows="2" cols="50">Please type your question!</textarea>
		  	<input id='post-question' class='btn mt-2' type = 'submit' value='Submit'/>
	  	</div> 
	  	
	  	<div class='mt-5'>
	  		<h3>You can also read our FAQ's!</h3>
	  		<div id='faq-body'>
	  			
	  		</div>
	  	</div> 
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<div class="modal" id="successModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Confirmed </h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

	<div class="modal-body" id='successModel_body'> 
		<h3>SUCCESS!!!</h3>
	  	
	</div>
        

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>

<script>
var slider = document.getElementById("priceRange");
var output = document.getElementById("priceValue");
output.innerHTML = slider.value;
slider.oninput = function() {
	output.innerHTML = this.value;
}
</script>
</body>
</html>