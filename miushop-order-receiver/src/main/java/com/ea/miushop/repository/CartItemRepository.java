package com.ea.miushop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ea.miushop.domain.CartItem;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//    List<CartItem> getAllByCart_CartId(Long id);
}
