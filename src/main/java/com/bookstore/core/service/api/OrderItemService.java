package com.bookstore.core.service.api;

import com.bookstore.db.model.entity.OrderItem;

import java.util.List;

/**
 * Created by keshav.gupta on 07/07/16.
 */
public interface OrderItemService {

    OrderItem findById( Integer bookOrderId, Integer bookId  ) ;
    List<OrderItem> findAll();
    OrderItem save(OrderItem entity);
    OrderItem update(OrderItem entity);
    OrderItem create(OrderItem entity);
    void delete( Integer bookOrderId, Integer bookId );

}
