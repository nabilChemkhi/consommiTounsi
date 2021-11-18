package com.consomiTounsi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.Order;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
//import com.itextpdf.text.log.Logger;
//import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;



public class GeneratePdfReport {

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream FactureReport(Order f) {

        Document document = new Document();
        
        
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
        	


            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(110);
            table.setWidths(new int[]{8,8,8,8,8,8});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
           // headFont.setBackgroundColor(BaseColor.LIGHT_GRAY);

            PdfPCell hcell;
          //  hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            
            hcell = new PdfPCell(new Phrase("Product",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
            
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

            hcell = new PdfPCell(new Phrase("Product price",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Quantity",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Amount",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
            
            
            
            hcell = new PdfPCell(new Phrase("Delevry fees ",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Total price",headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(hcell);

            
                PdfPCell cell;


                String s = new String("");
                //for(int i=0;i<f.getOrder().getCartItems().size();i++) 
                	for(int i=0;i<f.getCartItems().size();i++){
                	s += f.getCartItems().get(i).getProduct().getName()+"\n"+"\n";
                }
                	cell = new PdfPCell(new Phrase(s));
                    cell.setPaddingLeft(0);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);
                
                    String s1 = new String("");
//                    for(int i=0;i<f.getOrder().getCartItems().size();i++) {
//                    	s1 += String.valueOf(f.getOrder().getCartItems().get(i).getProduct().getPrice())+"\n"+"\n";
                    for(int i=0;i<f.getCartItems().size();i++) {
                    	s1 += String.valueOf(f.getCartItems().get(i).getProduct().getPrice())+"\n"+"\n";
                    }
                    cell = new PdfPCell(new Phrase(s1));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);
                
                    String s2 = new String("");
//                    for(int i=0;i<f.getOrder().getCartItems().size();i++) {
//                    	s2 += String.valueOf(f.getOrder().getCartItems().get(i).getQuanity())+"\n"+"\n";
                    for(int i=0;i<f.getCartItems().size();i++) {
                    	s2 += String.valueOf(f.getCartItems().get(i).getQuanity())+"\n"+"\n";
                    }
                    cell = new PdfPCell(new Phrase(s2));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);
                    
                
                    String s3 = new String("");
                   // for(int i=0;i<f.getOrder().getCartItems().size();i++) 
                    {
                    for(int i=0;i<f.getCartItems().size();i++)
                    
                    	
//                    s3 +=String.valueOf((f.getOrder().getCartItems().get(i).getQuanity())*(f.getOrder().getCartItems().get(i).getProduct().getPrice()))+"\n"+"\n";
                    	s3 +=String.valueOf((f.getCartItems().get(i).getQuanity())*(f.getCartItems().get(i).getProduct().getPrice()))+"\n"+"\n";
        }
                    cell = new PdfPCell(new Phrase(s3));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cell.setPaddingLeft(0);
                    table.addCell(cell);	

                
                cell = new PdfPCell(new Phrase(String.valueOf(f.getDelivFees())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setPaddingLeft(0);
                table.addCell(cell);
                
                //float price = (f.getOrder().getAmount())+(f.getDelivFees());
                float price = (f.getAmount())+(f.getDelivFees());
                cell = new PdfPCell(new Phrase(String.valueOf(price)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
              
                //Font pff = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                //p5F.setColor(BaseColor.);
                cell.setPaddingLeft(0);
                table.addCell(cell);
            
                //Paragraph elements = new Paragraph();
            PdfWriter.getInstance(document, out);
            document.open();
            
        	//Font mainFont = FontFactory.getFont("Arial", 50, BaseColor.BLACK);
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 30, Font.BOLD);

            Paragraph paragraph = new Paragraph("INVOICE", boldFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            
            Image img = 
            		Image.getInstance("./uploads//tounsi.jpg");
            		img.scaleToFit(100, 100);
            		document.add(img);
            
           // document.add(Image.getInstance("./uploads//tounsi.jpg"));
            
            document.add(paragraph);
            
            Paragraph p = new Paragraph("Number=:"+f.getId());
            p.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p);
            
           // Paragraph pp = new Paragraph("Date:" + f.getOrder().getOrderDate().toString());
            Paragraph pp = new Paragraph("Date:" + f.getOrderDate().toString());
            pp.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(pp);
            
            Paragraph p1 = new Paragraph("------------------------------------------");
                    p1.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p1);
                    Paragraph p3 = new Paragraph("------------------------------------------");
                    p3.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p3);
                    
                   // Paragraph p8 = new Paragraph("Custmor name:"+f.getOrder().getUser().getName());
                    Paragraph p8 = new Paragraph("Custmor name:"+f.getUser().getName());
                    p8.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p8);
                    Paragraph p6 = new Paragraph("------------------------------------------");
                    p6.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p6);
                    Paragraph p7 = new Paragraph("------------------------------------------");
                    p7.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p7);
                    
                    Paragraph p9 = new Paragraph("  ");
                    p9.setAlignment(Element.ALIGN_CENTER);
                    		
                    document.add(p9);
            
            document.add(table);
            
            Paragraph p2 = new Paragraph("------------------------------------------");
            p2.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p2);
            Paragraph p4 = new Paragraph("------------------------------------------");
            p4.setAlignment(Element.ALIGN_CENTER);
            		
            document.add(p4);
            
            Font p5F = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            //p5F.setColor(BaseColor.);
            Paragraph p5 = new Paragraph("#CONSOMMI TOUNSI 619",p5F );
           // Paragraph p5b = new Paragraph("#619",p5F);
            
            p5.setAlignment(Element.ALIGN_CENTER);

            		
            document.add(p5);
           // document.add(p5b);
            
            document.close();
            
            
        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
	
}
