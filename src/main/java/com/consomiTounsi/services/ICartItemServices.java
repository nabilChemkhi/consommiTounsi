package com.consomiTounsi.services;

import java.util.List;

import com.consomiTounsi.entities.CartItem;
import com.consomiTounsi.entities.Users;

public interface ICartItemServices {
	
	public void addToCart(Long prodId,int quantity,Long userId );
	public void updateItemWithPrdIdUserId(Integer quantity,long proId,long userId);
	public void removeByUserIdAndProdId(Long userdId,Long prodId );
	public List<CartItem> myCartItems(Users user);

}
