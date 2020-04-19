package com.ea.miushop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.miushop.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
