package com.bookstore.db.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by keshav.gupta on 07/07/16.
 */

@Data
@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "code")
    @JsonProperty(value = "code")
    private String code;

    @NotNull
    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @NotNull
    @Column(name = "address_line1")
    @JsonProperty(value = "address_line1")
    private String addressLine1;

    @NotNull
    @Column(name = "address_line2")
    @JsonProperty(value = "address_line2")
    private String addressLine2;

    @NotNull
    @Column(name = "pin_code")
    @JsonProperty(value = "pin_code")
    private Integer pinCode;

    @NotNull
    @Column(name = "city")
    @JsonProperty(value = "city")
    private String city;

    @NotNull
    @Column(name = "country_code")
    @JsonProperty(value = "country_code")
    private String countryCode;

    @Column(name = "phone")
    @JsonProperty(value = "phone")
    private String phone;

    @Column(name = "email")
    @JsonProperty(value = "email")
    private String email;



}
