package com.consomiTounsi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.consomiTounsi.Repository.BillRepository;
import com.consomiTounsi.Repository.CartItemRepository;
import com.consomiTounsi.Repository.OrderRepository;
import com.consomiTounsi.Repository.ProductRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.OrderStatus;
import com.consomiTounsi.entities.PaymentMethod;
import com.consomiTounsi.entities.Users;


@Service
public class OrederServicesImpl implements IOrderServices{
	//@Autowired
	//ShoppingCartRepository shoppCartRepo;
	@Autowired 
	ProductRepository productRepo;
	@Autowired 
	CartItemRepository cartItemRepo;
	@Autowired
	UsersRepository userRepo;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	BillRepository BillRepo;
	
	
/*********************** Find order by id / User / status/ ***************************************************************************************************************************/
/*	public Order findOrderById(Long id){
		return orderRepo.findById(id).get();
		
		
	}
*/

//find by user

public List<Order> findOrderByUser(Users user){
	List<Order> orders=orderRepo.finOrderByUserId( user.getId());
	return orders;
	
}

public List<Order> findOrderByStatus(OrderStatus status){
	List<Order> orders=orderRepo.finOrderByStatus( status);
	return orders;
	
}

//find all orders /admin role

public List<Order> finAllWithOrder(){
	return orderRepo.findAll(Sort.by("orderDate").descending());
}

//total number of orders/admin role
 public int countOrderNumber(){
	return orderRepo.countorder();
 }
 
 /********************************save and update order *********************************************************************************************************/
 public Long addOrUpdateOrder(Order order) {
		orderRepo.save(order);
		return order.getId();
	}
 
 public void saveOrder(Order order){
		
		orderRepo.save(order);
		
	}
 
 




/************** remove order by id ***********************************/
public void removeById(long orderId ){

	orderRepo.deleteById(orderId); 

}

/************** calcul order amount by id ***********************************/

public void calculAmountOrder(Long orderId) {
	
	
	Order order = orderRepo.findById(orderId).get();
	float amountOrder=0;

	List<CartItem> cartItems = (List<CartItem>) cartItemRepo.findAll();
	for(CartItem cartItem:cartItems){
if (cartItem.getOrder().getId()==(orderId))
{
amountOrder=amountOrder+cartItem.getAmount();
}

order.setAmount(amountOrder);
orderRepo.save(order);

}

}
/************** update order status ***********************************/

public void UpdateOrderStatus(long idorder, OrderStatus status) {
	 orderRepo.updateByStatus(status,  idorder);
		orderRepo.save(orderRepo.findById(idorder).get());
		//return order.getId();
}



/************** update order PaymentMethod ***********************************/

public void updateOrderPaymentMthd(long idorder, PaymentMethod status) {
	  //  Order ord = new Order();
	// ord=orderRepo.findById(idorder).get();
	
	 orderRepo.updateOrderPaymentMthd(status,  idorder);
		orderRepo.save(orderRepo.findById(idorder).get());
		//return order.getId();
		if (status==PaymentMethod.AFTERDELIVERY){
			UpdateOrderStatus(idorder,OrderStatus.REGISTRED);
			
//			if(orderRepo.findById(idorder).get().getBill()==null){
//			     genrateEnvoiceFromOrder(idorder);}
//			
			//set items dead
			
			List<CartItem> items= new ArrayList<CartItem>();
			items=orderRepo.findById(idorder).get().getCartItems();
			for(int i=0;i<items.size();i++){
				items.get(i).setStat(0);
				
				cartItemRepo.save(items.get(i));}
			
		 }
		else{
			UpdateOrderStatus(idorder,OrderStatus.PROCESSING);
			orderRepo.findById(idorder).get().setDelivFees(7);
			orderRepo.save(orderRepo.findById(idorder).get());
			
			List<CartItem> items= new ArrayList<CartItem>();
			items=orderRepo.findById(idorder).get().getCartItems();
			for(int i=0;i<items.size();i++){
				items.get(i).setStat(0);
				
				cartItemRepo.save(items.get(i));}
		}
}

/**************************generate envoice  **********************************************************************************************/

public Bill genrateEnvoiceFromOrder(Long orderId){
	
	Order order=orderRepo.findById(orderId).get();
	//if (order.getBill().getId() == null){ 
		
	
	Bill bill = new Bill();
/*  bill.setOrder(order) ;   */
	bill.setUser(order.getUser());
	bill.setDelivFees(7);
	BillRepo.save(bill);
/*	order.setBill(bill);  */
	//order.setOrderStatus(OrderStatus.NEW);
	orderRepo.save(order);
	
	
	return bill;
	//}
	//else return null;
	//return order.getBill();
	
}



}