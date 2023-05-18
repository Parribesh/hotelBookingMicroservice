package com.booking.util;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.booking.domain.Booking;
import com.booking.domain.Guest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

@Service
public class CreatePdf {

	 public void createPdf(String dest , Booking b, Object hotel) throws IOException {
	        // Initialize PDF document
	        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
	        
	        // Initialize document
	        Document document = new Document(pdf);
	        String hotelName = "Default Hotel";
	        
	        ObjectMapper mapper = new ObjectMapper();
	        
	       String hotelObj = mapper.writeValueAsString(hotel);
	       JsonNode rootNode = mapper.readTree(hotelObj);
	       hotelName = rootNode.get("hotelName").asText();
	       String hotel_address= rootNode.get("address").asText();
	       String hotel_city = rootNode.get("city").asText();
	       String hotel_state = rootNode.get("state").asText();
	       String hotel_mobile = rootNode.get("mobile").asText();
	       String hotel_email = rootNode.get("email").asText();
	        
	        // Add content
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
	        PdfFont bold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
	        PdfFont italic = PdfFontFactory.createFont((StandardFonts.TIMES_ITALIC));
	        
	        Text InvoiceNum = new Text("INVOICE# "+(int)Math.floor(Math.random()*100000)).setFont(bold);
	        
	        Paragraph invoice = new Paragraph().add(InvoiceNum);
	        document.add(invoice);
	        
	        Text title = new Text("Here is the Invoice For Your Booking!").setFont(bold);
	        Text author = new Text(hotelName).setFont(bold);
	        Text address = new Text(hotel_address);
	        Text city = new Text(hotel_city);
	        Text state = new Text(hotel_state);
	        Text phone = new Text(hotel_mobile);
	        Text email = new Text(hotel_email);
	        Paragraph p = new Paragraph().add(title);
	        Paragraph p1 = new Paragraph().add(" \n ").add(author).add("\n").add(address).add("\n").add(city).add(", ").add(state)
	        								.add("\nPhone: ").add(phone).setFont(italic).add("\nEmail: ").add(email).setFont(italic);
	        p.setTextAlignment(TextAlignment.CENTER);
	        p1.setTextAlignment(TextAlignment.RIGHT);
	        document.add(p);
	        document.add(p1);
	    
	        Paragraph gap = new Paragraph().add("\n");
	        document.add(gap);
	        
	        Paragraph p2 = new Paragraph("Booked On: "+ b.getBookedOnDate().toLocaleString());
	        p2.setTextAlignment(TextAlignment.LEFT);
	        document.add(p2);
	        
	        Paragraph p3 = new Paragraph("CheckIn Date: "+b.getCheckInDate());
	        p3.setTextAlignment(TextAlignment.RIGHT);
	        document.add(p3);

	        Table table = new Table(4);
	        table.setWidth(UnitValue.createPercentValue(100));
	        table.setHeight(500);
	        table.setTextAlignment(TextAlignment.LEFT);
	        table.setExtendBottomRow(true);

	        table.addHeaderCell(new Cell().add(new Paragraph("Guest Info ").setFont(bold)));
	        table.addHeaderCell(new Cell().add(new Paragraph("Days").setFont(bold))); 
	        table.addHeaderCell(new Cell().add(new Paragraph("Room No. ").setFont(bold)));    
	        table.addHeaderCell(new Cell().add(new Paragraph("Price ").setFont(bold)));
	        
	        long timeDiff = Math.abs(b.getCheckInDate().getTime() - b.getCheckOutDate().getTime());
	        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
	        
	        Cell guestCell = new Cell();
	        
	        for(Guest g: b.getGuests()) {
	        	Paragraph guestPara =new Paragraph("GuestId: "+ g.getGuestId()+ "\n Name: "+g.getFirstName()+" "+g.getLastName()).add("\nGender: "+g.getGender()).add("\nAge: "+g.getAge()+"\n\n\n");
	        	guestCell.add(guestPara);
	        }
	        table.addCell(guestCell);
	        table.addCell(new Cell().add(new Paragraph(daysDiff+"")));
	        table.addCell(new Cell().add(new Paragraph(b.getNoRooms()+"")));
			table.addCell(new Cell().add(new Paragraph("$"+ b.getPrice())));
	 
			table.addFooterCell(new Cell().add(new Paragraph("Total: ")));
			table.addFooterCell(new Cell().add(new Paragraph(daysDiff+"")));
			table.addFooterCell(new Cell().add(new Paragraph(" ")));
			table.addFooterCell(new Cell().add(new Paragraph("$"+(b.getPrice() * b.getNoRooms() * daysDiff))));
		
	        

	        document.add(table);
	        //Close document
	        document.close();
	    }
}
