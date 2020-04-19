package com.ea.miushop.service;

import com.ea.miushop.domain.StorageMovement;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StorageMovementService {
    List<StorageMovement> getAllStorageMovements();
    StorageMovement getStorageMovement(Long id);
    void makeStorageMovement(StorageMovement movement);
}
