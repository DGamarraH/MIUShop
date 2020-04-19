package com.ea.miushop.service.impl;

import com.ea.miushop.domain.Item;
import com.ea.miushop.domain.Order;
import com.ea.miushop.repository.ItemRepository;
import com.ea.miushop.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

//import com.ea.miushop.domain.User;
import com.ea.miushop.domain.Cart;
import com.ea.miushop.domain.CartItem;
//import com.ea.miushop_cart.emailservice.EmailService;
import com.ea.miushop.repository.CartRepository;
import com.ea.miushop.service.CartItemService;
import com.ea.miushop.service.CartService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
//	ApplicationContext context = new ClassPathXmlApplicationContext("context/applicationContext.xml");
	@Autowired
	CartService cartService;
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	private ApplicationContext context;

	@Autowired
	private OrderService orderService;

	@Autowired
	ItemRepository itemRepository;

	@Override
	public Cart getCart(Long id) {
		return cartRepository.findById(id).get();
	}

	@Override
	public Cart createCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart updateCart(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart addToCart(CartItem cartItem, Long cartId) {
		Cart cart = cartRepository.findById(cartId).get();
		cart.addItem(cartItem);
		cartItemService.saveItem(cartItem);
		return cartRepository.save(cart);
	}

	@Override
	public List<CartItem> getAllItemsInCart(Long cartId) {
		Cart cart = cartRepository.findById(cartId).get();
		List<CartItem> itemList = cart.getItemList();
		return itemList;
	}

	@Override
	public void checkOut(Long cartId) throws MessagingException {
//		EmailService emailService = (EmailService) context.getBean("emailService");
//		emailService.sendOrderReceivedMail(user.getFirstName(), user.getEmail(), cartItems, new Locale("en"));
		Cart cart = cartService.getCart(cartId);
		cart.getItemList().get(0);
//		List<CartItem> cartItems = cartItemService.getByCartId(cartId);
//		cart.setItemList(cartItems);
		Order order = new Order();
//		order.setUser(user);
//		cartItems = cartItemRepository.findAll();

		for(CartItem cartItem: cart.getItemList()){
			Item item = new Item();
			item.setOrder(order);
			item.setProduct(cartItem.getProduct());
			item.setQuantity(cartItem.getQuantity());
			order.getItems().add(item);
			itemRepository.save(item);
		}

		orderService.saveOrder(order);
		cartService.deleteCart(cartId);
//		RabbitTemplate makeOrderTemplate =  context.getBean("makeOrderTemplate",RabbitTemplate.class);
//		makeOrderTemplate.convertAndSend("make.order",order);
//		System.out.println("Order sent to make order queue");
	}

	@Override
	public void sendOrders(){
		List<Order> orderList = orderService.getAllOrders();

		RabbitTemplate makeOrderTemplate =  context.getBean("makeOrderTemplate",RabbitTemplate.class);
		makeOrderTemplate.convertAndSend("make.order",orderList);
		System.out.println("Order sent to make order queue");
	}

	@Override
	public CartItem getItem(Long cartId, Long itemId) {
		CartItem cartItem = cartItemService.getCartItem(itemId);
		List<CartItem> itemList = cartService.getAllItemsInCart(cartId);
		for (CartItem i : itemList) {
			if (i.getCartItemId().equals(cartItem.getCartItemId())) {
				return cartItem;
			}
		}
		return null;
	}

	@Override
	public void removeItem(Long itemId) {
		Cart cart = new Cart();
		List<CartItem> cartItems = cart.getItemList();
		for (int i = 0; i < cartItems.size(); i++) {
			if (cartItems.get(i).getCartItemId().equals(itemId))
				cartItems.remove(i);
		}
	}

	@Override
	public void deleteCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}
}
