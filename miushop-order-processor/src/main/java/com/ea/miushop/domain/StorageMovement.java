package com.ea.miushop.domain;

import javax.persistence.*;

import com.ea.miushop.validation.NullMinNumber;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Table(name = "storage_movements")
@Entity
public class StorageMovement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storageMovementId;

//    @NullMinNumber(value=1, message = "{NullMinNumber}")
    @Column(nullable = false)
    private StorageMovementType storageMovementType;

    //    @Temporal(TemporalType.TIMESTAMP)
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime movementDate = LocalDateTime.now();

    @Column(nullable = false)
    private Integer quantity;

    public StorageMovement() {
    }

    public Long getStorageMovementId() {
        return storageMovementId;
    }

    public void setStorageMovementId(Long storageMovementId) {
        this.storageMovementId = storageMovementId;
    }

    public StorageMovementType getStorageMovementType() {
        return storageMovementType;
    }

    public void setStorageMovementType(StorageMovementType storageMovementType) {
        this.storageMovementType = storageMovementType;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(LocalDateTime movementDate) {
        this.movementDate = movementDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
