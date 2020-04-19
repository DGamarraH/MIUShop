package com.ea.miushop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartid;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "cart_item_table")
	private List<CartItem> itemList = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "user_id")
	User user;

	private double totalPrice;

	public Long getCartId() {
		return cartid;
	}

	public void setCartId(Long cartid) {
		this.cartid = cartid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void addItem(CartItem cartItem) {
		itemList.add(cartItem);
	}
}
