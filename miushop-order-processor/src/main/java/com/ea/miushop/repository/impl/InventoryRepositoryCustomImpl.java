package com.ea.miushop.repository.impl;

import com.ea.miushop.domain.Inventory;
import com.ea.miushop.domain.StorageMovement;
import com.ea.miushop.repository.InventoryRepositoryCustom;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

@Component
public class InventoryRepositoryCustomImpl implements InventoryRepositoryCustom<Inventory> {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public List<StorageMovement> findStorageMovementCriteria(Long inventoryId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Typed query - expected results are of the type StorageMovement
        CriteriaQuery<Inventory> query = criteriaBuilder.createQuery(Inventory.class);
        // From part of the clause
        Root<Inventory> inventoryRoot = query.from(Inventory.class);
        // The Select
        query.select(inventoryRoot);
        // Where
        query.where(criteriaBuilder.equal(inventoryRoot.get("inventoryId"),  inventoryId) );

        Inventory inventory = entityManager.createQuery( query ).getSingleResult();

        return inventory.getMovements();
    }
}
