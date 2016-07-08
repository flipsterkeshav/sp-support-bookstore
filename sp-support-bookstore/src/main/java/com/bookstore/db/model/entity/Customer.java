package com.bookstore.db.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by keshav.gupta on 07/07/16.
 */


@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @NotNull
    @Size( min = 1, max = 5 )
    private String code;

    @NotNull
    @Column(name = "country_code")
    @JsonProperty(value = "country_code")
    private String countryCode;

    @NotNull
    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    @JsonProperty(value = "last_name")
    private String lastName;


    @Column(name = "age")
    @JsonProperty(value = "age")
    private Integer age;

    @Column(name = "city")
    @JsonProperty(value = "city")
    private String city;


    @Column(name = "pin_code")
    @JsonProperty(value = "pin_code")
    private Integer pinCode;

    @Column(name = "phone")
    @JsonProperty(value = "phone")
    private String phone;


}
