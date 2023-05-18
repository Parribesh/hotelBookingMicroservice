$(document).ready(function () {
	
    $("#searchBtn").click(function () {
		let searchText = $("#searchLocation").val();
		$('#tableBody tr').remove();
		
     	$.get("http://localhost:8282/getHotels/"+searchText, {},
     			function(response){
					 
					 $.each(response, function(Key1, b){
						 console.log(b);
						 let averageRatings = 5;
						 if (b.ratings.length > 0){
							 let total = b.ratings.length;; 
							 let sum =  b.ratings.map(e => e.ratings).reduce((a, b) => a+b);
							 averageRatings = sum/total;
						 }
						 
						 $("#tableBody").append(
							 	"<div style='margin-bottom: 20px' class= 'results d-flex w-100 border'>"+
							 	"<div><img src='"+b.imageURL+"' width='200px' height='200px'/></div>"+
							 	"<div style='background-color: #e6eaed; color: grey; width: 100%' class='d-flex flex-column'>"+
							 	"<div style='height: 50px;' class='d-flex justify-content-between' >"+
							 	"<div class='text-center h-100'><span class='hotel-name' style='font-size: 1.5rem; font-weight: bold; padding-left: 10px'>"+b.hotelName+"</span><span class='d-none'>"+b.hotelId+"</span></div><div>"+b.email+"</div><div>"+
							 	"<div style='display: flex; align-items: center; justify-content: center; height: 100%;padding: 4px; background-color: #e6eaed; color: green; border: 1px solid orange;'>"+
							 	"<span style='font-size: 2rem; color: grey'>$</span><span style='font-size: 1.5rem; font-weight: 500'>"
							 	+b.averagePrice+"<span style='font-size: .75rem'>+Tax</span></span></div></div></div>"+
							 	"<div style='flex-grow: 1;' class='ratings-td '><div class='ratings-container container w-100'><div class='w-100'>"+
							 	"<ul style='font-weight: bold' class='d-flex w-100'>"+b.amenities.map(e => ("<li class='px-4'>"+e.name)+ "</li>").join('')+"</ul></div><div><span class='average-ratings'>"+averageRatings.toFixed(2)+"*</span><span class='review-text'>Read Our Reviews</span><span class='d-none'>"+b.hotelId+
							 	"</span><span style='font-size: 1.1 rem; margin-left: 10px;font-weight: bold;' class='faq-tag'>Have a question?</span></div></div></div></div></div>"
							)
					 });
				 });
			 
				 $("#tableBody").on('click', '.review-text', function(){
					 	$("#reviewModal").modal();
					 	$("#reviewModal_modalBody div").remove();
					 	let hotelId = $(this).parent().children("span").eq(2).text();
					 	$.get("http://localhost:8282/getHotelById/"+hotelId, {}, 
						function(response){
							 res = response.ratings;
							$.each(response.ratings, function(Key1, rating){
								$("#reviewModal_modalBody").append("<div class=' card m-4 p-4'><div class='d-flex align-items-center justify-content-between'><div class='d-flex'><img class='profile-img m-4' width = '50px' height='50px' src='https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png'/><div><h5>"+
								rating.email+"</h5><div>"+rating.reviews+"</div></div></div><div><span class='average-ratings'>"+rating.ratings+"</span></div></div></div>")
							})
						});	
					 	
					})	
				
				 $("#tableBody").on('click', '.faq-tag', function(){
				 	$("#faqModal").modal();
				 	 let hotelId = $(this).parent().children("span").eq(2).text();
				 	 $("#faq-body div").remove();
				 	 	$.get("http://localhost:8282/getQuestionsById/"+hotelId, {}, 
						function(response){
							console.log(response);
							$("#faq-body").append(response.map(res => "<div class='card m-3'><div class='d-flex flex-column'><h4>"+res.question+"</h4><div>"+res.answer+"</div></div></div>"));
						});	
				 	 
				 	$("#post-question").click(function(){
						 	let question = $("#question_text").val();
						 	let data={hotelId, question};
					
						 	$.ajax({
							  url:"http://localhost:8282/createQuestion",
							  type:"POST",
							  data:JSON.stringify(data),
							  contentType:"application/json; charset=utf-8",
							  success: function(res){
							  	console.log(res);
							  	if(res === 'success'){
									  $("#successModal").modal();
								  }
							  }
							})
					 })
				 	
				})
					
					
     		
    });
    
    //Appending all Bookings to a table to show the booking records of
    //a particular user
    $("#getMyBookings").click(function(){
		$("#myBookingsModal").modal();
		$("#search-booking").click(function(){
			let phoneNumber = ($("#booking-phone").val());
			$.get("http://localhost:8282/api/getMyBookings/"+phoneNumber, {}, 
				function(response){
					response.map(e => {
						console.log(e);
						return $("#booking-show-table").append
						("<tr><td>"+e.bookingId+"</td><td>"+new Date(e.bookedOnDate).toLocaleDateString()+"</td>"+
						"<td>"+new Date(e.checkInDate).toLocaleDateString()+"</td>"+
						"<td>"+new Date(e.checkOutDate).toLocaleDateString()+"</td>"+
						"<td>"+e.customerMobile+"</td>"+
						"<td>"+e.price+"</td>"+
						"<td>"+e.status+"</td>"+
						"<td><a href='http://localhost:8282/api/editBookingById/"+e.bookingId+"'><button class='pl-1 btn btn-info'>Edit</button></a></td>"+
						"<td><a href='http://localhost:8282/api/cancelBookingById/"+e.bookingId+"'><button class='pl-1 btn btn-danger'>Cancel</button></a></td>"+
	
						+"</tr>").css('width', '100%');
					})
				});	
		});
		
		
		
		$("#booking-show-table").on('mouseenter', 'tr', function(){
			$(this).css('background-color', '#ADD8E6');
		}).on('mouseleave', 'tr', function(){
			$(this).css('background-color', 'white');
		})
		
	});
    
    $("#filterBtn").click(function(){
		var price = parseInt($("#priceValue").text());
		var flag = 0;
		
		$("#tableBody tr").each(function(){
			$(this).show();
			var hotelPrice = parseInt($(this).children("td").eq(6).text());
			var starRating = parseInt($(this).children("td").eq(7).text());
			
			$('.star_rating').each(function(){
				if(this.checked){
					var rating = $(this).val();
					if(rating == starRating){
						flag = 1;
					}
				}
			});
			
			if(flag ==0){
				$(this).hide();
			}else if(hotelPrice > price){
				alert();
				$(this).hide();
			}
		});
	});
	
	$("#tableBody").on('click', '.hotel-name', function(){
		 hotelName = $(this).text();
		 hotelId = $(this).parent().children("span").eq(1).text();
		$("#select_roomTypes option").remove();
		$.get("http://localhost:8282/getHotelById/"+hotelId, {}, 
			function(response){
				 res = response.hotelRooms;
				$.each(response.hotelRooms, function(Key1, value1){
					$("#select_roomTypes").append("<option>"+value1.type.name+"</option>")
				})
			});	
			
						
		$("#myModal").modal();
		$("#modal_hotelName").val(hotelName);
		$("#modal_noGuests").val($("#noGuests").val());
		$("#modal_checkInDate").val($("#checkInDate").val());
		$("#modal_checkOutDate").val($("#checkOutDate").val());
		$("#modal_noRooms").val(parseInt($("#noRooms").val()));
		
	})
	
	    $("#search_rooms").click(function(){
		 type = ($("#select_roomTypes").val());
		$("#select_room").modal();
		 roomDes = (res.filter(elem => elem.type.name == type))[0];
		 hotelRoomId = roomDes.hotelRoomId;
		$("#room_price").text(roomDes.price);
		$("#amenities li").remove();
		$.each(roomDes.amenities, function(key2, val2){
			$("#amenities").append("<li>"+val2.name+"</li>")
		});
			$("#policies li").remove();
			$("#policies").append("<li>"+roomDes.policies+"</li>");
			
			let checkIn = new Date($("#modal_checkInDate").val()+"T15:00");
			let myData = {checkIn , hotelId: hotelId, hotelRoomId: hotelRoomId};
						
		$.ajax({ url:"http://localhost:8282/api/getAvailableRooms",
			  type:"POST",
			  data:JSON.stringify(myData),
			  contentType:"application/json; charset=utf-8",
			  dataType:"json",
			  success: function(res){
			  	console.log(res);
			  	let val = res;
			  	$("#available_rooms").text(parseInt(roomDes.noRooms )- val);
			  	roomsAvailable = roomDes.noRooms - val;
			  	if(roomsAvailable <=0){
					$('#book_button').prop('disabled',true).css('opacity',0.5);
					$('#booking-error').text("Sorry! No more Rooms available at this date! Please choose another date");
				}
	
			  }
			})
	});
	
	
	  $("#book_button").click(function(){
		$("#bookingHotelRoomModal").modal();
		$("#booking_hotelName").val(hotelName);
		
		let username = $("#username").text();
			$.get("http://localhost:8282/user/"+username, {},
     			function(response){
					 let email = (response);
					 $("#booking_email").val(email);
				 });
		$("#booking_hotelId").val(hotelId);
		$("#booking_hotelRoomId").val(hotelId);
		$("#booking_noGuests").val($("#modal_noGuests").val());
		$("#booking_noRooms").val($("#modal_noRooms").val());
		$("#booking_checkInDate").val($("#modal_checkInDate").val());
		$("#booking_checkOutDate").val($("#modal_checkOutDate").val());
		$("#booking_roomType").val(type);
		$("#booking_discount").text(roomDes.discount);
		$("#booking_price").text(roomDes.price);
		
		$(".btn-confirm-booking").click(function(){
				let guests = $("#booking_noGuests").val();
				 count = parseInt(guests);
				$("#num_guests").val(count);
				$("#guestModal").modal();
				$("#modal-guest-form tbody").remove();
				$("#modal-guest-form h3").remove();
				
				for(let i=0; i<count; i++){
					$("#modal-guest-form").append("<table>"+
								"<tbody><h3>Guest Info "+(i+1)+"</h3><tr>"+
								"<tr><td>FirstName:</td><td><input type='text' id='firstname"+i+"'' class='form-control'/></td></tr>"+
								"<tr><td>LastName:</td><td><input type='text' id='lastname"+i+"'' class='form-control'/></td></tr>"+
								"<tr class='d-flex justify-content-between'><td>Gender</td><td><input class = 'm-2' type= 'radio' value='male' name='gender"+i+"'/>Male</td>"+
								"<td><input class='m-2' type= 'radio' value='female' name='gender"+i+"'/>FeMale</td></tr>"+
								"</tr><tr><td>Age:</td><td><input type= 'number' id='age"+i+"' class='form-control w-30'/></td><tr></tbody>"
				
												+"</table><hr></hr>");
				}
				
				
				let guestsObj = [];
				$(".btn-addGuests").click(function(){
					for(let j = 0; j<parseInt(count); j++){
						let gender = $("#modal-guest-form input[name=gender"+j+"]:checked").val();
						let guest = {firstName:$("#firstname"+j).val(), lastName: $("#lastname"+j).val(), gender:gender , age: $("#age"+j).val()};
						guestsObj.push(guest);
					}
					
							let data = 
						{
	
						  "hotelId": $("#booking_hotelId").val(),
						  "hotelRoomId": hotelRoomId,
						  'email': $("#booking_email").val(),
						  "noRooms": $("#booking_noRooms").val(),
						  "checkInDate": new Date( $("#booking_checkInDate").val()+ "T15:00"),
						  "checkOutDate": new Date($("#booking_checkOutDate").val()+"T00:00"),
						  "bookedOnDate": new Date(),
						  "status": "upcoming",
						  "price": $("#booking_price").text(),
						  "discount": $("#booking_discount").text(),
						  "customerMobile": $("#booking_customerMobile").val(),
						  "roomType": $("#booking_roomType").val(),
						  "guests": guestsObj,
						}
						
								 
								 
					$.ajax({
					  url:"http://localhost:8282/api/createBooking",
					  type:"POST",
					  data:JSON.stringify(data),
					  contentType:"application/json; charset=utf-8",
					  dataType:"json",
					  success: function(res){
					  	console.log(res);
					  }
					})
					
					$("#confirmModal").modal();
					$("#myModal").modal('hide');
					$("#bookingHotelRoomModal").modal('hide');
					$("#guestModal").modal('hide');
					$("#select_room").modal('hide');
					
				});
		
		});
		
			
	});
	
	
	
	
	
  });