package com.bookstore.db.model.entity;

import com.bookstore.db.model.enums.OrderStateEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Column(name = "shop_code")
    @JsonProperty(value = "shop_code")
    private String shopCode;

    @NotNull
    @Column(name = "customer_code")
    @JsonProperty(value = "customer_code")
    private String customerCode;

    @NotNull
    @Column(name = "date")
    @JsonProperty(value = "date")
    private DateTime date;

    @NotNull
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @JsonProperty(value = "state")
    private OrderStateEnum state;

}
