package com.consomiTounsi.Controller;

	import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.core.io.InputStreamResource;


import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpHeaders;
	import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.Repository.OrderRepository;
import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.services.BillServicesImpl;
import com.consomiTounsi.services.OrederServicesImpl;
	import com.consomiTounsi.util.GeneratePdfReport;


import java.nio.charset.StandardCharsets;
	//import org.apache.commons.io.IOUtils;

@RestController
public class BillRestController {
	
	
	
	@Autowired
    private BillServicesImpl billServ;
	@Autowired
	private OrederServicesImpl orderService;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired 
	OrderRestController orderContr;
	
	//@Autowired
	//ServletContext context;
	
	//http://localhost:8081/consomiTounsi/generateBillPdf/1
	
	//@RequestMapping( value ="/generateBillPdf/{idOrder}", method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
	@GetMapping(value ="/generateBillPdf",produces = MediaType.APPLICATION_PDF_VALUE)
	//@GetMapping("/generateBillPdf/{idOrder}")
   public String FactureReport()
   // public ResponseEntity<InputStreamResource> FactureReport(/*@PathVariable("idOrder") long idOrder*/)                         
	{
		//  /{idOrder}
//		Order o = orderContr.getUserOrdersMax();
//		long idOrder = o.getId();
		
		
		long idOrder = 64;
		 Order order  = orderRepo.findById(idOrder).get();
        // Bill bill = billServ.findBillByOrder(order);
         ByteArrayInputStream bis = GeneratePdfReport.FactureReport(order);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Bill.pdf");
      
          int size = bis.available();
        char[] theChars = new char[size];
        byte[] bytes = new byte[size];
        bis.read(bytes, 0, size);
        for (int i = 0; i < size;)
          theChars[i] = (char) (bytes[i++] & 0xff);
   
        File convertFile = new File("./uploads//Bill.pdf");
		try {
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(bytes);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
       return new String(theChars);
//		String s=new String(theChars);
//		int n = bis.available();
//		byte[] bytes1 = new byte[n];
//		bis.read(bytes1, 0, n);
//		/*String*/ s = new String(bytes1, StandardCharsets.UTF_8);
//		return s;
//		
		//String result = IOUtils.toString(bis, StandardCharsets.UTF_8);
       // return  String s = bytes.toString(StandardCharsets.UTF_8);
		//new String(( bis).readAllBytes(), StandardCharsets.UTF_8);
		
		
				//String ss = new String(Base64.ENCODE(bytes1));
		
	
//       return ResponseEntity
//                .ok()
//              .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//               .body(new InputStreamResource(bis));//bis  
//                
       
    }
	
	
	
	
	
	
	
	
	
	
	@GetMapping(value ="/generateBillPdf2",produces = MediaType.APPLICATION_PDF_VALUE)
	//@GetMapping("/generateBillPdf/{idOrder}")
  // public String FactureReport()
    public ResponseEntity<InputStreamResource> FactureReport2(/*@PathVariable("idOrder") long idOrder*/)                         
	{
	//  /{idOrder}
//			Order o = orderContr.getUserOrdersMax();
//			long idOrder = o.getId();
			
			
			long idOrder = 53;
		
		 Order order  = orderRepo.findById(idOrder).get();
        // Bill bill = billServ.findBillByOrder(order);
         ByteArrayInputStream bis = GeneratePdfReport.FactureReport(order);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=Bill.pdf");
      
          int size = bis.available();
        char[] theChars = new char[size];
        byte[] bytes = new byte[size];
        bis.read(bytes, 0, size);
        for (int i = 0; i < size;)
          theChars[i] = (char) (bytes[i++] & 0xff);
   
        File convertFile = new File("./uploads//Bill.pdf");
		try {
			convertFile.createNewFile();
			FileOutputStream fout = new FileOutputStream(convertFile);
			fout.write(bytes);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
       //return new String(theChars);
		//String s=new String(theChars);
		/*int n = bis.available();
		byte[] bytes1 = new byte[n];
		bis.read(bytes1, 0, n);
		String s = new String(bytes1, StandardCharsets.UTF_8);
		return s;*/
		
		//String result = IOUtils.toString(bis, StandardCharsets.UTF_8);
       // return  String s = bytes.toString(StandardCharsets.UTF_8);
		//new String(( bis).readAllBytes(), StandardCharsets.UTF_8);
		
		
				//String ss = new String(Base64.ENCODE(bytes1));
		
	
       return ResponseEntity
                .ok()
              .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
               .body(new InputStreamResource(bis));//bis  
                
       
    }
	
	
	
	
	

}
