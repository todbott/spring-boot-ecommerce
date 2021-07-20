package com.ecommerce.database;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Transactional
    @Query("select i from Item i where i.inWhoseCart = 'shop'")
    Iterable<Item> getShopItems();

    @Modifying
    @Transactional
    @Query("update Item i set i.inWhoseCart = ?1 where i.id = ?2")
    void itemBought(String whoseCart, Long id);

    @Modifying
    @Transactional
    @Query("update Item i set i.inWhoseCart = '' where i.id = ?1")
    void blankWhoseCart(Long id);

    @Modifying
    @Transactional
    @Query("update Item i set i.inWhoseCart = ?1 where i.id = ?2")
    void setWhoseCart(String whoseCart, Long id);
        
    @Query("select i from Item i where i.name like %?1% and i.inWhoseCart = 'shop'")
    Iterable<Item> getFilteredItems(String pattern);

    @Query("select i from Item i where i.inWhoseCart = ?1 or i.inWhoseCart = ?2")
    Iterable<Item> getUsersItems(String user, String useruser);

    @Query("select i from Item i where i.inWhoseCart = ?1")
    Iterable<Item> getOrders(String user);
    
}
