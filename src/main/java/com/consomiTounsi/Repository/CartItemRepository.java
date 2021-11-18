package com.consomiTounsi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Product;
import com.consomiTounsi.entities.Users;

@Repository
public interface CartItemRepository  extends JpaRepository<CartItem, Long>{
	
	//CartItem findByUserAndProduct(Users user, Product product);
	public List<CartItem> findByUserAndProduct(Users user, Product product);
	public CartItem findByProduct(Product product);
	public List<CartItem> findByUser(Users user);
	public Optional<CartItem> findById(Long id);
   
	@Modifying
    @Transactional
    @Query("UPDATE CartItem c SET c.quanity=:newQuanity where c.product.id=:proId AND c.user.id=:userId ")
    public void updateItemWithPrdIdUserId(@Param("newQuanity")Integer quantity, @Param("proId")long proId, @Param("userId")long userId);
   
    @Modifying
    @Query("DELETE FROM CartItem c where c.user.id=:userId AND c.product.id=:proId")
    public void deleteByUserIdAndProdId(@Param("userId")long userId,@Param("proId")long proId);
    
    
    
	@Query("SELECT count(*) FROM CartItem")
    public int counItem();
	
	//@Query("select c from CartItem c where c.id:= proId")
	//aList<CartItem> findById(@Param("proId")long ID);
	
	//@Query("select amount from CartItem")
	//public List<float> getItemPr();
}


 

