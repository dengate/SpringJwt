package com.amazon.stock.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category extends ShareEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryId;
    private String name;
    private String description;

    @ManyToMany()
    @JoinTable(name = "category_products",
            joinColumns = { @JoinColumn(name = "categoryId") },
            inverseJoinColumns = { @JoinColumn(name = "productId") })
    private List<Product> products;

}
