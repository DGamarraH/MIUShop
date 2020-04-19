package com.ea.miushop.service;

import org.springframework.stereotype.Service;

import com.ea.miushop.domain.CartItem;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public interface CartItemService {
    List<CartItem> getAllItems();
    void saveItem(CartItem cartItem);
	CartItem getCartItem(Long cartItemId);
//	List<CartItem> getByCartId(Long Id);
}
