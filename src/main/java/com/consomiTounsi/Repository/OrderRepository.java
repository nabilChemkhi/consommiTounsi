package com.consomiTounsi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.consomiTounsi.entities.Order;
import com.consomiTounsi.entities.OrderStatus;
import com.consomiTounsi.entities.PaymentMethod;
import com.consomiTounsi.entities.Users;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;
@Repository
public interface OrderRepository extends JpaRepository<Order , Long>{

	@Query("select DISTINCT o from Order o join o.user u where u.id=:userId ")
	public List<Order> finOrderByUserId(@Param("userId")Long userId);
	
	//@Query(value ="select * from Order   order by orderDate desc ",nativeQuery = true)
	@Query("select DISTINCT o from Order o")
	public List<Order> findAll();
	
	@Query("select  o from Order o  where o.orderStatus=:status ")
	public List<Order> finOrderByStatus(@Param("status")OrderStatus status);
	
	@Query("SELECT count(*) FROM Order")   
	public int countorder();
	
	
	
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.orderStatus=:status1 where o.id=:orderId")
    public void updateByStatus(@Param("status1")OrderStatus status, @Param("orderId")Long orderId);
    
    
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.paymentMethod=:status1 where o.id=:orderId")
    public void updateOrderPaymentMthd(@Param("status1")PaymentMethod status, @Param("orderId")Long orderId);
    
   // 
	@Query("SELECT sum(o.amount)  FROM Order o WHERE o.orderStatus=:status1 ")
	public Float TotalSales(@Param("status1")OrderStatus status);
	
	
	@Query(value = "SELECT p.name, SUM(l.quantity) AS TotalQuantity FROM Product p JOIN CartItem i on p.id=i.product_id JOIN order o on i.order_id =o.id WHERE o.orderStatus='REGISTRED' GROUP BY p.id ORDER BY SUM(i.quantity) DESC LIMIT 10", nativeQuery = true)
	public List<Object[]> Top10Product();
    
}
