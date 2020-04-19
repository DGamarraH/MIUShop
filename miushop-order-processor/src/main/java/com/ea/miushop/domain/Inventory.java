package com.ea.miushop.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Table(name = "inventory")
@Entity
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long inventoryId;

	@OneToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(nullable = false)
	private Integer quantity;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable ( name="inventory_movement", joinColumns={@JoinColumn(name="inventory_id")},
			inverseJoinColumns={ @JoinColumn(name="storage_movement_id", unique=true)} )
	@Valid
	private List<StorageMovement> movements = new ArrayList<StorageMovement>();

	@Version
	private int version = 0;

	public Inventory() {
	}

	public Long getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<StorageMovement> getMovements() {
		return movements;
	}

	public void setMovements(List<StorageMovement> movements) {
		this.movements = movements;
	}

	public void addMovements(StorageMovement movement) {
		this.movements.add(movement);
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
}
