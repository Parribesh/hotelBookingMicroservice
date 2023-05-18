$(document).ready(function(){
	let username = $("#username").text();
	$("#username").text($("#username").text().toUpperCase());
		$("#myBookingsModal").modal();
		var status = "upcoming";
		$.get("http://localhost:8282/user/"+username, {},
     			function(response){
					email = (response);
					console.log(email);
					
				 });
		$("#btn-upcoming").click(function(){
			status = "upcoming";
			$.get("http://localhost:8282/api/getMyBookingsByEmail/"+email, {}, 
			
				function(response){
					$("#profile-show-table tr").remove();
					response.map(e => {
					if(e.status.toLowerCase() === status || e.status.toLowerCase() === "ongoing"){
						return $("#profile-show-table").append
						("<tr><td>"+e.bookingId+"</td><td>"+new Date(e.bookedOnDate).toLocaleDateString()+"</td>"+
						"<td>"+new Date(e.checkInDate).toLocaleDateString()+"</td>"+
						"<td>"+new Date(e.checkOutDate).toLocaleDateString()+"</td>"+
						"<td>"+e.customerMobile+"</td>"+
						"<td>"+e.price+"</td>"+
						"<td>"+e.status.toUpperCase()+"</td>"+
						"<td><a class=' link btn btn-info' href='http://localhost:8282/api/editBookingById/"+e.bookingId+"'>Edit</a></td>"+
						"<td><a class=' link btn btn-danger' href='http://localhost:8282/api/cancelBookingById/"+e.bookingId+"'>Cancel</a></td>"+
	
						+"</tr>");
					}
				})
		
			});
		})
		
		$("#btn-completed").click(function(){
			status = "completed";
			$.get("http://localhost:8282/api/getMyBookingsByEmail/"+email, {}, 
					function(response){
						$("#profile-show-table tr").remove();
						response.map(e => {
							
						if(e.status.toLowerCase() === status){
							return $("#profile-show-table").append
							("<tr><td>"+e.bookingId+"</td><td class='d-none'>"+e.hotelId+"</td><td>"+new Date(e.bookedOnDate).toLocaleDateString()+"</td>"+
							"<td>"+new Date(e.checkInDate).toLocaleDateString()+"</td>"+
							"<td>"+new Date(e.checkOutDate).toLocaleDateString()+"</td>"+
							"<td>"+e.customerMobile+"</td>"+
							"<td>"+e.price+"</td>"+
							"<td>"+e.status.toUpperCase()+"</td>"+
							"<td colspan='2'><button class='btn-review link btn btn-info' >Review</button></td>"+
		
							+"</tr>");
						}
					})
		
			});
		})
			
			
		$("#profile-show-table").on('mouseenter', 'tr', function(){
			$(this).css('background-color', '#ADD8E6');
		}).on('mouseleave', 'tr', function(){
			$(this).css('background-color', 'white');
		})
		
		$("#profile-show-table").on('click', 'button.btn-review', function(){
			$("#reviewModal").modal();
			let bookingId = $(this).parent().parent().children("td").eq(0).text();
			let hotelId = $(this).parent().parent().children("td").eq(1).text();

			 $("#review-submit").click(function(){
			 let val1 = $("#review-question1").find(":selected").text();
			 let val2 = $("#review-question2").find(":selected").text();
			 let val3 = $("#review-question3").find(":selected").text();
			 let val4 = $("#review-question4").find(":selected").text();
			 let total = parseInt(val1)+ parseInt(val2)+parseInt(val3)+parseInt(val4);
			 
			 
			 let ratings= total/4;
			 let reviews = $("#review-text").val();
			 let ratingsObj ={bookingId, email, ratings, reviews};
			  
			  
		 	$.ajax({ url:"http://localhost:8282/hotel/addReview/"+hotelId,
				  type:"POST",
				  data:JSON.stringify(ratingsObj),
				  contentType:"application/json; charset=utf-8",
				  success: function(res){
				  	alert(res);
					$("#review-rating").text(total/4).css("font-size", '2rem');
				  }
				}) 
	
	 		})
		 
		})
		
			$("#question-btn").click(function(){
			$("#question-modal").modal();
			$.get("http://localhost:8282/getAllUnansweredQuestion", {},
				function(response){
					$("#questionModal_body").append(response.filter(e => e.answer == null).map(res => 
					"<div class='card m-3'><div class='d-flex flex-column'>"+
					"<h4>"+ res.question+"</h4><div>"
					+"<div ><div class='questionId d-none'>"+res.questionId+"</div>"+
					"<div class=' answer-btn btn'>Answer</div></div>"+
					"</div></div></div>"));
			});	
			
			$("#questionModal_body").on('click', '.answer-btn', function(){
				
				let questionId = $(this).parent().children("div").eq(0).text();
				$.get("http://localhost:8282/getSpecificQuestionById/"+ questionId, {},
				function(res){
					console.log(res);
					$("#answer-modal").modal();
					$("#question").append("<h3>"+res.question+"</h3>");
					$("#submit-answer").click(function(){
						let submitedAnswer = $("#answer-text").val();
						let answer = {questionId: res.questionId, hotelId: res.hotelId, question:res.question , answer: submitedAnswer};
						
						$.ajax({ url:"http://localhost:8282/submitAnswer",
						  type:"POST",
						  data:JSON.stringify(answer),
						  contentType:"application/json; charset=utf-8",
						  success: function(res){
						  	if(res === 'succss'){
								   $("#successModal").modal();
							  }
						  }
						}) 
					})
				});	
				
			})
			
		})
		

		
		
});








