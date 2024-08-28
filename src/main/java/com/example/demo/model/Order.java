package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


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

    @OneToMany(mappedBy = "order")
    //@JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderProduct> orderProductSet;

    @Column
    private BigDecimal totalPrice;
}
