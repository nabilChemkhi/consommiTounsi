package com.consomiTounsi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consomiTounsi.Repository.BillRepository;
import com.consomiTounsi.Repository.UsersRepository;
import com.consomiTounsi.entities.Bill;
import com.consomiTounsi.entities.Order;






@Service
public class BillServicesImpl implements IBillServices{
	@Autowired
	BillRepository billRepo;
	@Autowired
	UsersRepository userRepo;
	
	
//	
//	public Bill findBillByOrder(Order order) {
//			
//		return billRepo.findBillByOrder(order);
//	}
	
	public List<Bill> findMyBills() {
		
		return billRepo.findBillByUser(userRepo.findById((long) 1).get());
	}
	
	
	public void deleteMyBill(long billId) {
		billRepo.delete(billRepo.findById(billId).get());
		
	}
	

}
