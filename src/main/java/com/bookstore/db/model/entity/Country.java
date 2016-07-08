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
@Table(name = "book")

public class Country {


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
}
