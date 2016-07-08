package com.bookstore.db.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wordnik.swagger.converter.JodaDateTimeConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import javax.persistence.*;

//import org.joda.time.DateTime;

/**
 * Created by keshav.gupta on 07/07/16.
 */
@Slf4j
@Data
@MappedSuperclass
@EqualsAndHashCode(of = {"id"})
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonProperty(value = "id")
    private Long id;

    @Version
    @Column(name = "version")
    @JsonIgnore
    private Long version;

    @Column(name = "created_at")
    @JsonProperty(value = "created_at")
    //@Convert(converter = JodaDateTimeConverter.class)
    private DateTime createdAt;

    @Column(name = "updated_at")
    @JsonProperty(value = "updated_at")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime updatedAt;

}

