package com.amazon.stock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends ShareEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    private int storeId;
    private String name;
    private double amount;
    private String imgUrl;
    private String description;
    private int stock;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories;
}
