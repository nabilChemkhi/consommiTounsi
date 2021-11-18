package com.consomiTounsi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.consomiTounsi.Repository.OrderRepository;
import com.consomiTounsi.Repository.ProductRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.OrderStatus;
import com.consomiTounsi.entities.PaymentMethod;
import com.consomiTounsi.entities.Users;
import com.consomiTounsi.services.CartItemServicesImpl;
import com.consomiTounsi.services.OrederServicesImpl;



@RestController
public class OrderRestController {
	@Autowired
	UsersRepository userRepo;
	@Autowired
	OrederServicesImpl orderServ;
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	CartItemServicesImpl itemServ;
	@Autowired
	ProductRepository productRepo;

	
	
/*	// http://localhost:8081/consoumiTounsi/addOrder
	//{"id":1,"nom":"kallel", "prenom":"khaled", "email":"Khaled.kallel@ssiiconsulting.tn", "isActif":true, "role":"INGENIEUR"}
	
	@PostMapping("/addOrder")
	@ResponseBody
	public Employe ajouterEmploye(@RequestBody Employe employe)
	{
		iemployeservice.addOrUpdateEmploye(employe);
		return employe;
	}
	
	*/
	
	
	 // URL : http://localhost:8081/consomiTounsi/get-all-Orders-byUser/1
	@GetMapping("/getOrderById/{idUser}")
	@ResponseBody
	public Order getOrderById(@PathVariable(value = "idUser")long id) {
		
		Order  ord=orderRepo.findById(id).get();
		
		return ord;			
	}
	
	/*********************** Find order  User  ***************************************************************************************************************************/

	 // URL : http://localhost:8081/consomiTounsi/get-all-Orders-byUser/1
		@GetMapping("/get-all-Orders-byUser/{idUser}")
		@ResponseBody
		public List<Order> getUserOrders(@PathVariable(value = "idUser")long id) {
			
			Users  user=userRepo.findById(id).get();
			List<Order> list = orderServ.findOrderByUser( user);
			return list;			
		}
		
		
		/*********************** Find order  User  ***************************************************************************************************************************/

		 // URL : http://localhost:8081/consomiTounsi/get-all-Orders-byUser/1
			@GetMapping("/getLastOrder")
			@ResponseBody
			public Order getUserOrdersMax() {
				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				Users user0 = userRepo.findbyUserName(auth.getName());
				long id0=user0.getId();
				
				List<Order> list =getUserOrders(id0);
				long b=0;
				for(int i = 1; i < list.size(); i++)
				{
					if (list.get(i).getId() > b){
						
						b=list.get(i).getId();
					}
				}
				
				 Order ord =orderRepo.findById(b).get();
				 return ord;
							
			}
		/********************** get order by status **************************************/

		 // URL : http://localhost:8081/consomiTounsi/get-all-Orders-byStatus/"NEW"
			@GetMapping("/get-all-Orders-byStatus/{status}")
			@ResponseBody
			public List<Order> getOrdersByStatus(@PathVariable(value = "status")OrderStatus status) {
				
				
				List<Order> list = orderServ.findOrderByStatus( status);
				return list;			
			}
			
			/********************** Totl sales **************************************/

			 // URL : http://localhost:8081/consomiTounsi/totalSales
				@GetMapping("/totalSales")
				@ResponseBody
				public float totalSales() {
					OrderStatus status=OrderStatus.REGISTRED;
				
					return 		orderRepo.TotalSales(OrderStatus.REGISTRED);
					//return 1;
				}
		
			/********************** get all orders **************************************/

		
		//find all orders /admin role
		 // URL : http://localhost:8081/consomiTounsi/get-all-Orders-Desc
			@GetMapping("/get-all-Orders-Desc")
			@ResponseBody
			public List<Order> getAllOrders() {
				
				
				List<Order> list = orderServ.finAllWithOrder( );
				return list;			
			}
			/********************** get all order number **************************************/

			//total number of orders/admin role
		    // URL : http://localhost:8081/consomiTounsi/countOrders
		    @GetMapping(value = "/countOrders")
		    @ResponseBody
			public int getNombreOrdersJPQL() {
				
				return orderServ.countOrderNumber();
			}
		/********************** Remove order by id**************************************/
			//remove Order 
			//http://localhost:8081/consomiTounsi/rmoveOrder/{idOrder}
			@Transactional
			@DeleteMapping("/rmoveOrder/{id}")
			public void removeOrderById(@PathVariable(value = "id")long orderId ){

				orderServ.removeById(orderId);
		
			}

			/********************** calcul order amount order by id**************************************/

			//http://localhost:8081/consomiTounsi/calculAmountOrder/{ordId}
			@PutMapping("/calculAmountOrder/{ordId}")
			public void calculAmountOrder(@PathVariable(value = "ordId")long ordId){
				orderServ.calculAmountOrder(ordId);
			}
			

			/********************** updateOrderStatus**************************************/

			//http://localhost:8081/consomiTounsi/updateOrderStatus/{ordId}/{satatus}
			@PutMapping("/updateOrderStatus/{ordId}/{satatus}")
			public void UpdateOrderStatus(@PathVariable(value = "ordId")long ordId,@PathVariable(value = "satatus")OrderStatus status){
				//Order o = orderRepo.getOne(ordId);
				 orderServ.UpdateOrderStatus(ordId,status);
			}
			
			/********************** PaymentMethod**************************************/

			//http://localhost:8081/consomiTounsi/updateOrderPaymentMthd/{ordId}/{satatus}
			@PutMapping("/updateOrderPaymentMthd/{ordId}/{satatus}")
			public void updateOrderPaymentMthd(@PathVariable(value = "ordId")long ordId,@PathVariable(value = "satatus")PaymentMethod status){
				//Order o = orderRepo.getOne(ordId);
				 orderServ.updateOrderPaymentMthd(ordId,status);
				 
			}
			
			/********************** create Bill from order **************************************/

			//http://localhost:8081/consomiTounsi/genrateEnvoiceFromOrder/{idOrder}
			@PostMapping("/genrateEnvoiceFromOrder/{orderId}")
			public Bill genrateEnvoiceFromOrder(@PathVariable(value = "orderId")long orderId){
				return orderServ.genrateEnvoiceFromOrder(orderId);
			}
			
			
			
			@PutMapping("/update/{id}" )
			public Order UpdateUser (@PathVariable Long id ,@RequestBody Order u){
				//u.setId(id);
				Order ord = getUserOrdersMax();
				//ord.setPaymentMethod(u.getPaymentMethod());
				ord.setId(id);
				updateOrderPaymentMthd(id,u.getPaymentMethod());
				orderRepo.save(ord);
			 
				return u; //orderRepo.save(u);
				//return getUserOrdersMax();
//				orderRepo.findById(id).get().setDelivFees(u.getDelivFees());
//				orderRepo.save(u);
//				 return orderRepo.findById(id).get();
			}
}
