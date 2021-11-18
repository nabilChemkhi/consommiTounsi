package com.consomiTounsi.Controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.consomiTounsi.ChoppingTest;
import com.consomiTounsi.Repository.BillRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.ChargeRequest;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.Users;
import com.consomiTounsi.services.StripeServices;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;



import org.apache.log4j.Logger;
import org.junit.*;

@RestController
public class StripRestController {
	
	@Autowired
	StripeServices stripeServ;
	@Autowired
	BillRepository billRepo;
	@Autowired
	UsersRepository userRepo;
	@Autowired 
	OrderRestController orderContr;
	
	private static final Logger L = Logger.getLogger(StripRestController.class);

	
	
//	http://localhost:8081/consomiTounsi/createcharge//1

	@RequestMapping(value="/createcharge/{token}/{orId}",method = RequestMethod.GET)
    @ResponseBody
    public String  createCharge(/*@PathVariable("email")String email,*/@PathVariable("token") String token
    		                    /*@PathVariable("amount")  Long amount*/,@PathVariable("orId")long ido)
    {
        L.info("Enter >> createCharge() ");
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user0 = userRepo.findbyUserName(auth.getName());
		long id0=user0.getId();
		

        if(token == null)
        {
            throw new RuntimeException("Can't create a charge, try again");
        }

       // Bill billing = billRepo.findById(companyid).get(); //(Integer.parseInt(companyid));

        //double billedAmount = amount * 100;

        String chargeId = stripeServ.createCharge(user0.getLastName(),token,ido);

        if(chargeId != null && !chargeId.equals(""))
        	
        {
        	//update bill
        	L.info("bill has been charged on consumer's account");
           // billing.setDelivFees(1111111); //Status(true);
            //billing.setPaiddate(new Date());
           // billRepo.save(billing);
        }

        L.info("Exit << createCharge() ");
       // return new Response(true,"Congratulations, your card has been charged, chargeId= "+chargeId);
        //return new String("Congratulations, your card has been charged, chargeId= "+chargeId);
        return new String("Congratulations! your payment was successful, chargeId= "+chargeId);

    }
	/*******************************/
	//http://localhost:8081/consomiTounsi/customer1/{idUser}
	@PostMapping("/customer1/{idUser}")
	@ResponseBody
	public String createCustomer(@PathVariable("idUser") int idUser) {
		
		
		L.info("in Createcustmor");
		return stripeServ.createStripeCustomer(idUser);
	}
	/************************************Create custmor*************/
	// http://localhost:8081/consomiTounsi//customer/cus_JCw8EVVOxKiBQH/4242424242424242/11/2026/123
	@PostMapping("/customer/{cartNmbr}/{expMonth}/{expYear}/{cvc}")
	@ResponseBody
	// @PreAuthorize("hasRole('USER')")
	public String createCustomer(/*@PathVariable("customerId") int customerId,*/ @PathVariable("cartNmbr") String cartNmbr,
			@PathVariable("expMonth") String expMonth, @PathVariable("expYear") String expYear,
			@PathVariable("cvc") String cvc) throws StripeException {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user0 = userRepo.findbyUserName(auth.getName());
		long id0=user0.getId();
		
		return stripeServ.createCustumorStripe(id0, cartNmbr, expMonth, expYear, cvc);
	}   //tok_1IaWYxLDwpwISJZGTAsYaXGj
	
	/*********************************/
	// http://localhost:8081// http://localhost:8081/consomiTounsi/paymentintent
	/*
	 * { "description":"test la methode payment", "amount":"10000",
	 * "currency":"eur" }
	 */
	
	@PostMapping("/paymentintent"/*, method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE*/)
	// @PreAuthorize("hasRole('USER')")
	public String payment(@RequestBody ChargeRequest chargeRequest) throws StripeException {
		
		return stripeServ.paymentIntent(chargeRequest);
	}
/************************************/
	// http://localhost:8081/consomiTounsi/confirm{id}/{idCommande}
	@PostMapping("/confirm/{id}/{idCommande}")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> confirm(@PathVariable("id") String id,@PathVariable("idCommande") int idCommande /*, @PathVariable("idUser") int idUser*/) throws StripeException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user0 = userRepo.findbyUserName(auth.getName());
		long id0=user0.getId();
		
		PaymentIntent paymentIntent = stripeServ.confirm(id,idCommande,id0);
		String paymentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentStr, HttpStatus.OK);

}
	/***********************************/
	// http://localhost:8081/consomiTounsi/pay/{id}/{carta}/{expMonth}/{expYear}/{cvc}
//	@PostMapping("/pay/{idOrd}/{cartNbr}/{expMonth}/{expYear}/{cvc}")
//	public void Pay(@PathVariable("idOrd") long ordId,@PathVariable("cartNbr") String cartNbr,
//			@PathVariable("expMonth") int expMonth, @PathVariable("expYear") int expYear,@PathVariable("cvc") String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
//		{
//			
//			
//			
//			stripeServ.Pay(ordId,cartNbr,expMonth,expYear,cvc);
//		}
//	}
	
	
	@PostMapping("/pay/{cartNbr}/{expMonth}/{expYear}/{cvc}")
	public void Pay(/*@PathVariable("idOrd") long ordId,*/@PathVariable("cartNbr") String cartNbr,
			@PathVariable("expMonth") int expMonth, @PathVariable("expYear") int expYear,@PathVariable("cvc") String cvc) throws AuthenticationException, InvalidRequestException, CardException, StripeException{
		{
			
			Order o = orderContr.getUserOrdersMax();
			 
			
			stripeServ.Pay(o.getId(),cartNbr,expMonth,expYear,cvc);
		}
	}
	
	
}
