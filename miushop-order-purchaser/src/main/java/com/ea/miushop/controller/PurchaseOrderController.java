package com.ea.miushop.controller;

import com.ea.miushop.domain.Order;
import com.ea.miushop.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    PurchaseOrderService purchaseOrderService;

    @PostMapping(value = "send-bought-orders", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public void sendBoughtOrders(){
        purchaseOrderService.sendBoughtOrders();
    }
}
