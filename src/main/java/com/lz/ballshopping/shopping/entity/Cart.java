package com.lz.ballshopping.shopping.entity;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable {

    List<ShoppingCart> items;

    public List<ShoppingCart> getItems() {
        return items;
    }

    public void setItems(List<ShoppingCart> items) {
        this.items = items;
    }
}
