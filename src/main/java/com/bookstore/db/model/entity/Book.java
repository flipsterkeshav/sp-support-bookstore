package com.bookstore.db.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "publisher_id")
    @JsonProperty(value = "publisher_id")
    private Integer publisherId;

    @NotNull
    @Column(name = "author_id")
    @JsonProperty(value = "author_id")
    private Integer authorId;


    @NotNull
    @Column(name = "isbn")
    @JsonProperty(value = "isbn")
    private String isbn;

    @NotNull
    @Column(name = "title")
    @JsonProperty(value = "title")
    private String title;

    @NotNull
    @Column(name = "price")
    @JsonProperty(value = "price")
    private BigDecimal price;


    @NotNull
    @Column(name = "quantity")
    @JsonProperty(value = "quantity")
    private Integer quantity;


    @NotNull
    @Column(name = "discount")
    @JsonProperty(value = "discount")
    private Integer discount;


    @NotNull
    @Column(name = "availability")
    @JsonProperty(value = "availability")
    private Short availability;

}
