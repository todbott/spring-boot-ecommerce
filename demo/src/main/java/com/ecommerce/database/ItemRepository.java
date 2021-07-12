package com.ecommerce.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {


    @Modifying
    @Query("update Item i set i.inWhoseCart = ?1 where i.id = ?2")
    void setWhoseCart(String whoseCart, Long itemId);
        
    @Query("select i from Item i where i.name like %?1%")
    Iterable<Item> getFilteredItems(String pattern);
    
}
