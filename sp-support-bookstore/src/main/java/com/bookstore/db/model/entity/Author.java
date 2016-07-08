package com.bookstore.db.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "author")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty(value = "last_name")
    private String lastName;
}
