package com.ea.miushop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service

public class BatchJob {

	@Autowired
	private CartService cartService;

	@Scheduled(cron = " 0 0/1 * 1/1 * ? ")
	public void deleteProduct() {
//		System.out.println("Deleting cart...");
//		cartService.deleteCart(111111111L);

	}

}
