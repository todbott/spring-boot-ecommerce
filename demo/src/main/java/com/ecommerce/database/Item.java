package com.ecommerce.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Float price;
    private String picture;

  
    public Integer getId() {
      return id;
    }
  
    public void setId(Integer id) {
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
        return price.toString();
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }

    public String getPicture () {
        return picture;
    }

    public void setPicture (String picture) {
        this.picture = picture;
    }

}
