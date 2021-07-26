package com.ecommerce.database;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Transactional
    @Query("select i from Item i where i.inWhoseCart = 'shop'")
    Iterable<Item> getShopItems();

    @Query("select count(i) FROM Item i where i.inWhoseCart = 'shop' and i.name = ?1")
    Integer countShopItems(String name);

    @Modifying
    @Transactional
    @Query("update Item i set i.inWhoseCart = ?1 where i.inWhoseCart = ?2 and i.name = ?3")
    void itemBought(String whoseCart, String username, String itemName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE item SET in_whose_cart = ?2 WHERE in_whose_cart = ?1 AND name = ?3 LIMIT ?4", nativeQuery = true)
    void setWhoseCart(String oldCart, String newCart, String itemName, Integer quantity);
        
    @Query("select i from Item i where i.name like %?1% and i.inWhoseCart = 'shop'")
    Iterable<Item> getFilteredItems(String pattern);
    
    @Query("select i from Item i where i.inWhoseCart = ?1")
    Iterable<Item> getUsersItems(String user);

    @Query("select count(i) FROM Item i where i.inWhoseCart = ?1 and i.name = ?2")
    Integer countUsersItems(String user, String itemName);

    @Query("select i from Item i where i.inWhoseCart = ?1")
    Iterable<Item> getOrders(String user);

    @Query(value = "delete i from item i where name = ?1", nativeQuery = true)
    void deleteByName(String itemName);
    
}
