package com.ecommerce.database;

import javax.persistence.Entity;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)

  private long id;
    private String name;
    private String description;
    private String price;
    private String picture;
    private String inWhoseCart;

  
    public Long getId() {
      return id;
    }
  
    public void setId(Long id) {
      this.id = id;
    }
  
    public String getName() {
      return name;
    }
  
    public void setName(String name) {
      this.name = name;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture () {
        return picture;
    }

    public void setPicture (String picture) {
        this.picture = picture;
    }

    public String getInWhoseCart () {
      return inWhoseCart;
    }

    public void setInWhoseCart (String inWhoseCart) {
      this.inWhoseCart = inWhoseCart;
    }



}
