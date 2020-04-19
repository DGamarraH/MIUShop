package com.ea.miushop.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Service;

import com.ea.miushop.domain.Cart;
import com.ea.miushop.domain.CartItem;

@Service
public interface CartService {
    Cart getCart(Long id);
    Cart createCart(Cart cart);
    Cart updateCart(Cart cart);
    Cart addToCart(CartItem cartItem, Long cartId);
	void removeItem(Long itemId);
	List<CartItem> getAllItemsInCart(Long cartId);
	CartItem getItem(Long cartId, Long itemId);
	void deleteCart(Long cartId);
	void checkOut(Long cartId) throws MessagingException;
	void sendOrders();

}
