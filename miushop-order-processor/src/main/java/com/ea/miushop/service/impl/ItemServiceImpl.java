package com.ea.miushop.service.impl;

import com.ea.miushop.domain.Item;
import com.ea.miushop.domain.Order;
import com.ea.miushop.repository.ItemRepository;
import com.ea.miushop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.getOne(id);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public List<Item> findAllByOrder(Order order) {
        return itemRepository.findAllByOrder(order);
    }
}
