package com.ea.miushop.domain;

import javax.persistence.*;

import com.ea.miushop.validation.EmptyOrSize;

import java.io.Serializable;

@Entity
@Table(name = "products")
public class Product implements Serializable{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @EmptyOrSize(min=4, max = 20, message= "{EmptyOrSize}")
    @Column(nullable = false)
    private String productName;

    private String productWalmartLink;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private Category category;
    
    @Column(nullable = false)
    private Boolean catalogEnabled = true;    
    
    @Version
    private int version = 0;

    public Product() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductWalmartLink() {
        return productWalmartLink;
    }

    public void setProductWalmartLink(String productWalmartLink) {
        this.productWalmartLink = productWalmartLink;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Boolean getCatalogEnabled() {
        return catalogEnabled;
    }

    public void setCatalogEnabled(Boolean catalogEnabled) {
        this.catalogEnabled = catalogEnabled;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
