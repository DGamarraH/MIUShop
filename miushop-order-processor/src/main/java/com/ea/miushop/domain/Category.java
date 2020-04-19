package com.ea.miushop.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.ea.miushop.validation.EmptyOrSize;

import com.ea.miushop.validation.groups.*;

import java.io.Serializable;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @EmptyOrSize(min=4, max = 20, message= "{EmptyOrSize}", groups={Details.class})
    @Column(nullable = false)
    private String categoryName;

    @Version
    private int version = 0;

    public Category() {
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
