package com.consomiTounsi.services;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.CartItemRepository;
import com.consomiTounsi.Repository.OrderRepository;
import com.consomiTounsi.Repository.ProductRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.OrderStatus;
import com.consomiTounsi.entities.PaymentMethod;
import com.consomiTounsi.entities.Product;
import com.consomiTounsi.entities.Users;



@Service
public class CartItemServicesImpl implements ICartItemServices{
	@Autowired 
	ProductRepository productRep;
	@Autowired 
	CartItemRepository cartItemRep;
	@Autowired
	UsersRepository userRepo;
	@Autowired
	OrderRepository oredrRepo;
	
	

	
	 

	

	
/**********************       ADD item to shopping cart ***********************/                	
	private int newQtt ;
	@Override
	public void addToCart(Long prodId,int quantity,Long userId ){ //
		newQtt= quantity;
		
		Users user=userRepo.findById(userId).get();
		Product product = productRep.findById(prodId).get(); 
		//CartItem cartItem =null;
//		List<CartItem> cartItems = cartItemRep.findByUserAndProduct(user,product);
//		for(CartItem cartItem0:cartItems){
//			if ((cartItem0.getOrder()==null))// || 
//				{cartItem=cartItem0;}
//			if (cartItem.getOrder().getOrderStatus().compareTo(OrderStatus.NEW) == 0)
//				{cartItem=cartItem0;}
			
			
		//}
		
		//CartItem cartItem = cartItemRep.findByUserAndProduct(user,product); 
		List<CartItem> cartItems = cartItemRep.findByUserAndProduct(user,product);
		if ((cartItems.size()==0) && (newQtt > 0)){
			
			
//				 if (newQtt <= 0){
//					return;// return "quantity null!"; return;
//				 }
				// if(newQtt > 0){
					CartItem cartItem=new CartItem();
					cartItem.setStat(1);
					
					cartItem.setProduct(product);
					cartItem.setUser(user);
					//cartItem.setPrice(product.getPrice());
			        cartItem.setQuanity(newQtt);
			        cartItem.setAmount(newQtt*product.getPrice()); 
			        cartItemRep.save(cartItem);
			       // return ;
			    // }
		}
			else{
		for (int i=0; i<cartItems.size();i++){
			if (cartItems.get(i).isStat() == 1)
			{
			
			if ((cartItems.get(i) !=  null)  ){	 
				 newQtt =cartItems.get(i).getQuanity()+quantity;
				 if (newQtt <= 0){
					 cartItemRep.delete(cartItems.get(i));
					 return ;
				 }
				 else{
					 cartItems.get(i).setQuanity(newQtt);
					 cartItems.get(i).setAmount(newQtt*product.getPrice());
				 cartItemRep.save(cartItems.get(i));
				return ;
				 }
				 
			 }
			}
		}
			}
			 
//			 else{
//				 if (newQtt <= 0){
//					return ;
//				 }
//				 else{
//					CartItem cartItem2=new CartItem();
//					cartItem2.setStat(true);
//					
//					cartItem2.setProduct(product);
//					cartItem2.setUser(user);
//					//cartItem.setPrice(product.getPrice());
//					cartItem2.setQuanity(newQtt);
//					cartItem2.setAmount(newQtt*product.getPrice()); 
//			        cartItemRep.save(cartItem2);
//			       // return " new item added!";
//			     }

			  
			       // System.out.println("added to cart");
			        
				
		
		//if product exist=update quantity
		 
			 
			// if (  (cartItem.getOrder()==null))//(cartItem.getOrder().getOrderStatus().compareTo(OrderStatus.NEW)== 0) ||
			 
//		if ((cartItem !=  null)  ){	 
//			 newQtt =cartItem.getQuanity()+quantity;
//			 if (newQtt <= 0){
//				 cartItemRep.delete(cartItem);
//				 //return "item deleted succ!";
//			 }
//			 else{
//			 cartItem.setQuanity(newQtt);
//			 cartItem.setAmount(newQtt*product.getPrice());
//			 cartItemRep.save(cartItem);
//			// return "quantity updated!";
//			 }
//			 
//		 }
//		 
//		 else{
//			 if (newQtt <= 0){
//				// return "quantity null!";
//			 }
//			 else{
//				cartItem=new CartItem();
//				cartItem.setStat(true);
//				
//				cartItem.setProduct(product);
//				cartItem.setUser(user);
//				//cartItem.setPrice(product.getPrice());
//		        cartItem.setQuanity(newQtt);
//		        cartItem.setAmount(newQtt*product.getPrice()); 
//		        cartItemRep.save(cartItem);
//		       // return " new item added!";
//		     }
//
//		 }  
//		       // System.out.println("added to cart");
//		        
//			}
		}
	
	
	/****************** update quantity by prod and user service **************************/
	@Override
	public void updateItemWithPrdIdUserId(Integer quantity,long proId,long userId)
	{  
		if (quantity<=0){
			cartItemRep.deleteByUserIdAndProdId(userId, proId);
		}
		else{
		//update quantity
		cartItemRep.updateItemWithPrdIdUserId(quantity, proId, userId);
		 //update amount
		Product product1=productRep.findById(proId).get();
		 Users user1=userRepo.findById(userId).get();
		
		 float newAmount = product1.getPrice()*quantity;
		 CartItem item=new CartItem();
		// item=cartItemRep.findByUserAndProduct(user1,product1);//setAmount(newAmount);
		 item.setAmount(newAmount);
				cartItemRep.save( item);
				//item.setAmount(newAmount);
		}
				
	}
	
	/******************* Remove Item by user is and prod id  ********************************/
	@Override
	public void removeByUserIdAndProdId(Long userdId,Long prodId ){
		Users user1=userRepo.findById(userdId).get();
		Product product1=productRep.findById(prodId).get();
		
		//CartItem item=cartItemRep.findByUserAndProduct(user1,product1);
		//cartItemRep.delete(item);
		
		List<CartItem> cartItems = cartItemRep.findByUserAndProduct(user1,product1);
		for (int i=0; i<cartItems.size();i++){
			if (cartItems.get(i).isStat() == 1)
		cartItemRep.deleteByUserIdAndProdId(userdId, prodId);
		}
		
	}
	
	

	/******************* myCart  ********************************/
	/************************************************/
	
	
		public List<CartItem> myCart(Long userId){
			Users user1=userRepo.findById(userId).get();
		    List<CartItem> allItems = new ArrayList<>();
		    cartItemRep.findByUser(user1).forEach(allItems::add);
		    //return cartItems;
		    List<CartItem> cartItems = new ArrayList<>();
		    for(int i=0;i<allItems.size();i++){
		    	if (allItems.get(i).getOrder()==null || allItems.get(i).getOrder().getOrderStatus().equals(OrderStatus.NEW))
		    	{
		    		cartItems.add(allItems.get(i));
		    		
		    	}
		    	//items.get(i).setOrder(order);
				//cartItemRep.save(items.get(i));
			}
	    	return cartItems;

		    
		}
		
		/**********************       myCartItems ***********************/
		@Override
		public List<CartItem> myCartItems(Users user){
			
			System.out.println("My car items");
			return cartItemRep.findByUser(user);
			
		}
		
		/**********************       numerb of cart items  ***********************/
		public int counItem() {
			return cartItemRep.counItem();
		}
		/////////////////
		/**********************       List of cart items  ***********************/
		public List<CartItem> getAllCartItems() {
			List<CartItem> cartItems = (List<CartItem>) cartItemRep.findAll();
			
			//for(CartItem cartItem:cartItems){
				//System.out.println("commandLine +++:"+cartItem);
				//L.info("commandLine +++:"+cartItem);
			//}
			return cartItems;
		}
		
		/************* my cart amount ************/
		public float myCartAmount(Long userId){
		float cartAmount=0;
		List<CartItem> cartItems = myCart(userId);;
		for(CartItem cartItem:cartItems){
	
			cartAmount=cartAmount+cartItem.getAmount();
		}
	  return cartAmount;
		}
/**********************       checkoutCart  ***********************/

		public Order checkoutCart(Long userId){
			//List<Order> ordres= new ArrayList<>();
			//ordres=oredrRepo.finOrderByUserId(userId);
			
			List<CartItem> items = myCart(userId);
			if (items.isEmpty()){
				return null;}
			else{
			Users user=userRepo.findById(userId).get();
			float amount=myCartAmount(userId);
			Order order= new Order();
			order.setCartItems(items);
			order.setUser(user);
			order.setAmount(amount);
			order.setOrderStatus(OrderStatus.NEW);
			order.setDelivFees(7);
			order.setPaymentMethod(PaymentMethod.AFTERDELIVERY);
			//order.setPaymentMethod(PaymentMethod.ONLINE);
			LocalDateTime localDateTime = LocalDateTime.now();
			order.setOrderDate(localDateTime);
			oredrRepo.save(order);
			for(int i=0;i<items.size();i++){
				items.get(i).setOrder(order);
				
				cartItemRep.save(items.get(i));
			}
			//order.getId();

			return order;
			}
		}
		
	
	
}
