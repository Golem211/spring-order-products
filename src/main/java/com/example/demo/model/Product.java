package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProductSet;


    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private int stock;

    public void decreaseStock(int nrOfProducts){
        if(getStock() > nrOfProducts) {
            setStock(getStock() - nrOfProducts);
        }

    }
}
