package com.consomiTounsi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.Repository.CartItemRepository;
import com.consomiTounsi.Repository.ProductRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.Product;
import com.consomiTounsi.entities.Users;
import com.consomiTounsi.services.CartItemServicesImpl;


@RestController
//@RequestMapping("/Cart")
public class CartItemRestController {
	@Autowired
	CartItemServicesImpl itemServ;
	@Autowired
	UsersRepository userRepo;
	@Autowired
	CartItemRepository itemRepo;
	
	
	@Autowired
	ProductRepository prodRepo;
	
	
	
	  /********************************* get all prods  ************************************************/
	
	 // URL : http://localhost:8081/consomiTounsi/get-all-Products
		@GetMapping("/get-all-Products")
		@ResponseBody
		public List<Product> getproducts() {
			
			//Product list = prodRepo.findAll().get(0);
			//Users  user=userRepo.findById((long) 1).get();
			//List<CartItem> list = itemServ.myCartItems( user);
			//return list;	
			
			
				return (List<Product>) prodRepo.findAll();
			
		}
	
/********************************* get number of items  ************************************************/

    // URL : http://localhost:8081/consomiTounsi/counItem
    @GetMapping(value = "counItem")
    @ResponseBody
	public int getNombreItemJPQL() {
		
		return itemServ.counItem();
	}
  /********************************* get all cart items  ************************************************/
	
 // URL : http://localhost:8081/consomiTounsi/get-all-CartItems
	@GetMapping("/get-all-CartItems")
	@ResponseBody
	public List<CartItem> getCartItems() {
		
		List<CartItem> list = itemServ.getAllCartItems();
		//Users  user=userRepo.findById((long) 1).get();
		//List<CartItem> list = itemServ.myCartItems( user);
		return list;			
	}

		
/********************************* add  prodct to my cart ************************************************/

		//http://localhost:8081/consomiTounsi/addToCart/3/55	
	    @PostMapping("/addToCart/{idprod}/{qtt}"/*/{userId}"*/)
		public	void addToCart(@PathVariable(value = "idprod")long proId,@PathVariable(value = "qtt")Integer quantity/*,@PathVariable(value = "userId")Long userId */){
	    	
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Users user0 = userRepo.findbyUserName(auth.getName());
			long id0=user0.getId();
			
	    	itemServ.addToCart(proId, quantity, id0);
		}
		
/********************************* updat  item quantity by uder and prodct  ************************************************/
		
		//http://localhost:8081/consomiTounsi/updateQuantity/{qtt}/{idprod}
	    @Transactional
		@PutMapping("/updateQuantity/{idprod}/{qtt}"/*/{idUser}"*/)
	    public void updateItemWithPrdIdUserId(@PathVariable(value = "idprod")long proId,@PathVariable(value = "qtt")Integer quantity/*,@PathVariable(value = "idUser")long userId*/){

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Users user0 = userRepo.findbyUserName(auth.getName());
			long id0=user0.getId();
	    	
			itemServ.updateItemWithPrdIdUserId(quantity, proId, id0);
		}
		
/********************************* remove item by uder and prodct  ************************************************/

		//remove item from cart
		//http://localhost:8081/consomiTounsi/rmoveItem/{idprod}
		@Transactional
		@DeleteMapping("/rmoveItem/{idprod}")
		public void removeByUserIdAndProdId(/*@PathVariable(value = "idUser")long userdId,*/@PathVariable(value = "idprod")long prodId ){
		
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Users user0 = userRepo.findbyUserName(auth.getName());
			long id0=user0.getId();
			
			itemServ.removeByUserIdAndProdId(id0, prodId);
	
		}

/********************************* get  cart items by user id ************************************************/
		 // URL : http://localhost:8081/consomiTounsi/get-myCart/1
			@GetMapping("/get-myCart/{idUser}")
			@ResponseBody
		public List<CartItem> myCart(@PathVariable(value = "idUser")long  userId){
			return itemServ.myCart(userId);
			
			
		}
			
	/********************************* /show-my-Cart-items ************************************************/
			
			 // URL : http://localhost:8081/consomiTounsi//show-my-Cart-items
				//@GetMapping("/get-all-CartItems-byUser/{idUser}")
			
			@GetMapping("/show-my-Cart-items")
			//@PreAuthorize("hasRole('USER')")
			//@PreAuthorize("hasRole('Administrator')")
				@ResponseBody
				public List<CartItem> getUserCartItems(/*@PathVariable(value = "idUser")long id*/) {
					////Optional<Users>///////////////////
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					Users user0 = userRepo.findbyUserName(auth.getName());
					long id0=user0.getId();
					System.out.println("aa"+user0.getId().toString() +"aaa");
					

					//Users  user=userRepo.findById(id).get();
					List<CartItem> list = itemServ.myCart( id0);
					return list;			
				}
			
			/********************************* get number of my items  ************************************************/

		    // URL : http://localhost:8081/consomiTounsi/my-cart-size
		    @GetMapping(value = "my-cart-size")
		    @ResponseBody
			public int getNombreItemJPQL1() {
		    	List<CartItem> myCart = getUserCartItems();
		    	return myCart.size();
				//return itemServ.counItem();
			}

/********************************** checkout cart  ***********************************************/	

			 //  http://localhost:8081/consomiTounsi/checkout-cart
			@PostMapping("/checkout-cart") ///
		    //@GetMapping("/checkout-cart")
			@ResponseBody
			public Order checkoutCart(/*@PathVariable(value = "idUser")long  userId*/){
				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Users user0 = userRepo.findbyUserName(auth.getName());
				long id0=user0.getId();
				
				return itemServ.checkoutCart(id0);
				 
			}
	

 
}


