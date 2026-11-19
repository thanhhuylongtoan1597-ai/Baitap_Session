package org.example.business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

public class Product implements Serializable {

    private String id;
    private String description;
    private double price;

    public Product() {
        id = "";
        description = "";
        price = 0;
    }

    public Product(String code, String description, double price) {
        this.id = code;
        this.description = description;
        this.price = price;
    }

    public String getid() {
        return id;
    }

    public void setid(String code) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
        return currency.format(price);
    }
}