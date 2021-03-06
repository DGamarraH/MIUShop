package com.ea.miushop.service.impl;

import java.util.List;

import com.ea.miushop.domain.Inventory;
import com.ea.miushop.domain.Product;
import com.ea.miushop.domain.StorageMovement;
import com.ea.miushop.domain.StorageMovementType;
import com.ea.miushop.repository.InventoryRepository;
import com.ea.miushop.repository.InventoryRepositoryCustom;
import com.ea.miushop.repository.StorageMovementRepository;
import com.ea.miushop.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Autowired
	InventoryRepositoryCustom<Inventory> inventoryRepositoryCustom;
	
	@Autowired
	StorageMovementRepository storageMovementRepository;

	@Override
	public List<Inventory> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	public Inventory getInventory(Long id) {
		return inventoryRepository.findById(id).get();
	}

	@Override
	public void enterInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
	}
	
	@Override
	public void updateInventory(Long inventoryId, StorageMovement storageMovement) {

		int sign = 1;
		StorageMovementType movementType = storageMovement.getStorageMovementType();

		storageMovementRepository.save(storageMovement);

		switch (movementType) {
			case IN:
				sign = 1;
				break;
			case OUT:
				sign = -1;
				break;
		}
		int finalSign = sign;
		
//		Inventory modifiedInventory = entityManager.getReference(Inventory.class, inventoryId);
			
		inventoryRepository.findById(inventoryId)
				.map( inventory -> {
					inventory.setQuantity( inventory.getQuantity() + (finalSign * storageMovement.getQuantity()));
					inventory.addMovements(storageMovement);
					return inventoryRepository.save(inventory);
				});
	}

	@Override
	public void updateInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
	}

	@Override
	public Integer getInventoryQuantity(Product product) {
		Inventory inventory = inventoryRepository.findByProduct(product);
		System.out.println(product.getProductName());
		return inventory.getQuantity();
	}

	@Override
	public Inventory getInventoryByProduct(Product product) {
		return inventoryRepository.findByProduct(product);
	}

	@Override
	public Integer getInventoryQuantity(Long productId) {
//		Inventory inventory = inventoryRepository.findByProduct_ProductId(productId);
//		System.out.println(product.getProductName());
		return inventoryRepository.findByProduct_ProductId(productId).getQuantity();
	}

	@Override
	public Inventory getInventoryByProduct(Long productId) {
		return inventoryRepository.findByProduct_ProductId(productId);
	}

	@Override
	public List<Inventory> findAllSubSelect() {
		// Need to Hydrate because of LAZY load
		List<Inventory> inventoryList = this.getAllInventory();
		inventoryList.get(0).getMovements().get(0);
		return inventoryList;
	}

	@Override
	public List<StorageMovement> findStorageMovements(Long inventoryId) {
		return inventoryRepositoryCustom.findStorageMovementCriteria(inventoryId);
	}

}
