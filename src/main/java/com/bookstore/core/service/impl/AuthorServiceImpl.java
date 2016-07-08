package com.bookstore.core.service.impl;

/**
 * Created by keshav.gupta on 08/07/16.
 */

import com.bookstore.db.dao.api.AuthorDao;
import com.bookstore.core.service.api.AuthorService;
import com.bookstore.db.model.entity.Author;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of AuthorService
 */
@Slf4j
public class AuthorServiceImpl implements AuthorService {


    private AuthorDao authorJpaRepository;

    //private AuthorServiceMapper authorServiceMapper;

    @Inject
    public AuthorServiceImpl(AuthorDao areaDao) {
        this.authorJpaRepository =areaDao;
    }



    @Override
    public Author findById(Integer id) {
        Author author = authorJpaRepository.findOne(id);
        return author;
        //return authorServiceMapper.mapAuthorEntityToAuthor(authorEntity);
    }

    @Override
    public List<Author> findAll() {
        Iterable<Author> entities = authorJpaRepository.findAll();
        List<Author> beans = new ArrayList<Author>();
        for(Author authorEntity : entities) {
            beans.add(authorEntity);
        }
        return beans;
    }

    @Override
    public Long save(Author author) {
        return update(author) ;
    }

    @Override
    public Long create(Author author) {
        Author authorEntity = authorJpaRepository.findOne(author.getId());
        if( authorEntity != null ) {
            throw new IllegalStateException("already.exists");
        }
        authorEntity = new Author();
        //authorServiceMapper.mapAuthorToAuthorEntity(author, authorEntity);
        Long authorEntitySaved = authorJpaRepository.save(authorEntity);
        return authorEntitySaved;
    }

//    @Override
//    public void delete(Integer id) {
//
//    }

    @Override
    public Long update(Author author) {
        Author authorEntity = authorJpaRepository.findOne(author.getId());
        //authorServiceMapper.mapAuthorToAuthorEntity(author, authorEntity);
        Long authorEntitySaved = authorJpaRepository.save(authorEntity);
        return authorEntitySaved;
    }

//    @Override
//    public void delete(Integer id) {
//        authorJpaRepository.delete(id);
//    }

//    public AuthorJpaRepository getAuthorJpaRepository() {
//        return authorJpaRepository;
//    }

//    public void setAuthorJpaRepository(AuthorJpaRepository authorJpaRepository) {
//        this.authorJpaRepository = authorJpaRepository;
//    }

//    public AuthorServiceMapper getAuthorServiceMapper() {
//        return authorServiceMapper;
//    }
//
//    public void setAuthorServiceMapper(AuthorServiceMapper authorServiceMapper) {
//        this.authorServiceMapper = authorServiceMapper;
//    }

}

