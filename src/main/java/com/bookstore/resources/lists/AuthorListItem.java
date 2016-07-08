package com.bookstore.resources.lists;

import com.bookstore.db.model.entity.Author;

/**
 * Created by keshav.gupta on 08/07/16.
 */
public class AuthorListItem implements ListItem {

    private final String value ;
    private final String label ;

    public AuthorListItem(Author author) {
        super();

        this.value = ""
                + author.getId()
        ;

        //TODO : Define here the attributes to be displayed as the label
        this.label = author.toString();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

}
