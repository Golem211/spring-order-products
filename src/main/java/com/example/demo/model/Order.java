package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ORDERS")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private Integer orderNumber;

    @Column
    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProductSet;

    @Column
    private BigDecimal totalPrice;
}
