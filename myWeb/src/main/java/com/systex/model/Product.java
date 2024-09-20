/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.systex.model;

/**
 *
 * @author Administrator
 */
public class Product {
    private String id;
    private String name;
    private String stock;
    private String price;

    public Product(String id, String name, String stock, String price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    

}
