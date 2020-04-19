package com.ea.miushop.aspect;

import com.ea.miushop.domain.Inventory;
import com.ea.miushop.domain.StorageMovement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InventoryAspect {

    @Pointcut("within(com.ea.miushop.service..*)")
    public void printOutServices() {}

    @Pointcut("args(inventory)")
    public void argsInventory(Inventory inventory) {}

    @Pointcut("args(inventoryId, movement)")
    public void argsMovement(Long inventoryId, StorageMovement movement) {}

    @After("printOutServices() &&  argsInventory(inventory) ")
    public void logInventoryCreated(JoinPoint joinPoint, Inventory inventory ) {
        System.out.println();
        System.out.println( "   **********     INVENTORY CREATED : " +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName() +
                "    **********");
        System.out.println( "      ********   INVENTORY : " +
                inventory.getInventoryId() + " VERSION: " + inventory.getVersion() +
                "   ********");
    }

    @After("printOutServices() &&  argsMovement(inventoryId, movement) ")
    public void logInventoryUpdated(JoinPoint joinPoint, Long inventoryId, StorageMovement movement ) {
        System.out.println();
        System.out.println( "   **********     INVENTORY UPDATED : " +
                joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName() +
                "    **********");
        System.out.println( "      ********   STORAGE MOVEMENT  ********" );
        System.out.println(" ID: " + inventoryId );
        System.out.println(" MOVEMENT TYPE: " + movement.getStorageMovementType());
        System.out.println(" MOVEMENT DATE: " + movement.getMovementDate());
        System.out.println(" MOVEMENT QUANTITY: " + movement.getQuantity());
    }

}
