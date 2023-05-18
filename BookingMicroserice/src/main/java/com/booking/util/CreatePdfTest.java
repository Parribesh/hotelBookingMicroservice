package com.booking.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.booking.domain.Booking;
import com.booking.domain.Guest;
import com.booking.repository.BookingRepository;
import com.booking.service.BookingService;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class CreatePdfTest {
	
	public static final String DEST = "invoices/testInvoice.pdf";
	
	
	 public static void createPdf(String dest) throws IOException {
	        // Initialize PDF document
	        PdfDocument pdf = new PdfDocument(new PdfWriter(dest));
	        
	        // Initialize document
	        Document document = new Document(pdf);
	        
	        
	        
	        // Add content
	        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
	        PdfFont bold = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
	        PdfFont italic = PdfFontFactory.createFont((StandardFonts.TIMES_ITALIC));
	        
	        Text InvoiceNum = new Text("INVOICE# "+(int)Math.floor(Math.random()*100000)).setFont(bold);
	        
	        Paragraph invoice = new Paragraph().add(InvoiceNum);
	        document.add(invoice);
	        
	        Text title = new Text("Here is the Invoice For Your Booking!").setFont(bold);
	        Text author = new Text("Gilroy Inn Hotel").setFont(bold);
	        Text address = new Text("123 main St");
	        Text city = new Text("Gilroy, ");
	        Text state = new Text("CA - ");
	        Text zip = new Text("95020");
	        Paragraph p = new Paragraph().add(title);
	        Paragraph p1 = new Paragraph().add(" \n ").add(author).add("\n").add(address).add("\n").add(city).add(state).add(zip);
	        p.setTextAlignment(TextAlignment.CENTER);
	        p1.setTextAlignment(TextAlignment.RIGHT);
	        document.add(p);
	        document.add(p1);
	    
	        Paragraph gap = new Paragraph().add("\n");
	        document.add(gap);
	        
	        Paragraph p2 = new Paragraph("Booked On: "+"02/12/22");
	        p2.setTextAlignment(TextAlignment.LEFT);
	        document.add(p2);
	        
	        Paragraph p3 = new Paragraph("CheckIn Date: "+"02/12/22");
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
	        
	        table.addCell(new Cell().add(new Paragraph("Name: "+"Paribesh").add("\nGender: "+"Neupane").add("\nAge: "+"18")));
	        table.addCell(new Cell().add(new Paragraph("3")));
	        table.addCell(new Cell().add(new Paragraph("3")));
			table.addCell(new Cell().add(new Paragraph("$20.99")));
			
			table.addFooterCell(new Cell().add(new Paragraph("Total: ")));
			table.addFooterCell(new Cell().add(new Paragraph("3")));
			table.addFooterCell(new Cell().add(new Paragraph(" ")));
			table.addFooterCell(new Cell().add(new Paragraph("$300 ")));
		
	        

	        document.add(table);
	        //Close document
	        document.close();
	    }
	 
	 
	 
	 public static void main( String[] args) throws IOException {
		 	File file = new File(DEST);
			file.getParentFile().mkdir();
			createPdf(DEST);
		}

	}

