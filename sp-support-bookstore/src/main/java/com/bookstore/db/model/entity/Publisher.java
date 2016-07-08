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
@Table(name = "publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "code")
    @JsonProperty(value = "code")
    private Integer code;

    @NotNull
    @Column(name = "country_code")
    @JsonProperty(value = "country_code")
    private String countryCode;

    @NotNull
    @Column(name = "name")
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "email")
    @JsonProperty(value = "email")
    private String email;

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
