package com.bookstore.db.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Column(name = "order_id")
    @JsonProperty(value = "order_id")
    private Integer orderId;

    @NotNull
    @Column(name = "book_id")
    @JsonProperty(value = "book_id")
    private Integer bookId;


    @NotNull
    @Column(name = "quantity")
    @JsonProperty(value = "quantity")
    private Integer quantity;

    @NotNull
    @Column(name = "price")
    @JsonProperty(value = "price")
    private BigDecimal price;

}
