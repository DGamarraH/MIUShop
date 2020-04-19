package com.ea.miushop.repository;

import com.ea.miushop.domain.StorageMovement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Component
public interface InventoryRepositoryCustom<Inventory> {

    public List<StorageMovement> findStorageMovementCriteria(Long inventoryId) ;
}
