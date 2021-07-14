package com.ecommerce.securedControllers;

import java.util.ArrayList;

public class Combo {
    private ArrayList<String> items;
    private ArrayList<Long> ids;

    public Combo(ArrayList<String> items, ArrayList<Long> ids) {
  
        this.items = items;
        this.ids = ids;
    }
}
