package com.ea.miushop.controller;

import com.ea.miushop.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

//import com.ea.miushop_cart.controller.OrderController;
import com.ea.miushop.domain.Cart;
import com.ea.miushop.domain.CartItem;
import com.ea.miushop.service.CartItemService;
import com.ea.miushop.service.CartService;

import java.util.List;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	CartService cartService;
	@Autowired
	CartItemService itemService;
	
	@GetMapping(value = "{cartId}")
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public Cart getCartById(@PathVariable Long cartId) {
		return cartService.getCart(cartId);
	}

	@GetMapping(value = "items/{cartId}")
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public List<CartItem> getAllItemsInCart(@PathVariable Long cartId) {
		return cartService.getAllItemsInCart(cartId);
	}

	@GetMapping(value = "itembyId/{cartId}/{itemId}")
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public CartItem getItem(@PathVariable Long cartId, @PathVariable Long cartItemId) {
		return cartService.getItem(cartId, cartItemId);
	}

	@PostMapping(value = "new-cart", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	@PreAuthorize("hasRole('USER') ")
	public void createCart(@RequestBody Cart cart) {
		cartService.createCart(cart);
	}

	@PostMapping(value = "update-cart", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public void updateCart(@RequestBody Cart cart) {
		cartService.createCart(cart);
	}

	@PostMapping(value = "add-item/{cartId}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public void addItem(@RequestBody CartItem cartItem, @PathVariable Long cartId) {
		cartService.addToCart(cartItem, cartId);
}

	@PostMapping(value = "checkout/{cartId}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public void checkout(@PathVariable Long cartId) throws MessagingException {
		cartService.checkOut(cartId);

	}

	@PostMapping(value = "send-orders", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	@PreAuthorize("hasRole('USER') or hasRole('PURCHASER') or hasRole('ADMIN')")
	public ResponseEntity<?> sendOrder() {
		cartService.sendOrders();
		return ResponseEntity.ok(new MessageResponse("Your order has been sent to the exchange"));
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/delete/{cartItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@PreAuthorize("hasRole('USER') ")
	public void removeFromCart(@PathVariable("cartItem") Long itemId) {
		cartService.removeItem(itemId);
	}

}
