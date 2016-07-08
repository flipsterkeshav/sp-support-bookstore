package com.bookstore.resources.controllers;

import com.bookstore.resources.lists.AuthorListItem;
import com.codahale.metrics.annotation.Timed;
import com.bookstore.core.service.api.AuthorService;
import com.bookstore.db.model.entity.Author;
import com.google.inject.Inject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by keshav.gupta on 08/07/16.
 */
@Path("/author")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@Api(value = "/author", description = "Author specific apis")
public class AuthorResource {
    private final AuthorService authorService;

    @Inject
    public AuthorResource(AuthorService authorService){
        this.authorService = authorService;
    }

    @GET
    @Timed
    @Path("/all")
    @ApiOperation(value = "Get all author names", response = String[].class)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @GET
    @Timed
    @Path("/all/list")
    @ApiOperation(value = "Get all author names in list format", response = String[].class)
    @Produces(MediaType.APPLICATION_JSON)
    public List<AuthorListItem> findAllAsListItems() {
        System.out.println("hello");
        List<Author> list = authorService.findAll();
        List<AuthorListItem> items = new LinkedList<AuthorListItem>();
        for ( Author author : list ) {
            items.add(new AuthorListItem( author ) );
        }
        //System.out.println(items);
        return items;
    }

    @GET
    @Timed
    @Path("/{id}")
    @ApiOperation(value = "Get all author names in list format", response = String[].class)
    @Produces(MediaType.APPLICATION_JSON)
    public Author findOne(@PathParam("id") Integer id) {
        return authorService.findById(id);
    }

    @POST
    @Timed
    @Path("/{id}")
    @ApiOperation(value = "create new author", response = String[].class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Author author) {
        try {
            authorService.create(author);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            log.error("Error while adding BGV details for hero", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }


    }


    @PUT
    @Path("/{id}")
    @ApiOperation(value = "update author", response = String[].class)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Integer id, Author author) {

        try {
            authorService.update(author);
            return Response.status(Response.Status.OK).build();
        }catch (Exception e){
            log.error("Error while updating author details", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}