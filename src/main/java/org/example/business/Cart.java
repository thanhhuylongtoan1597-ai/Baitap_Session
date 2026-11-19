package org.example.business;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<LineItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item) {
        String id = item.getProduct().getid();
        int quantity = item.getQuantity();

        for (LineItem cartItem : items) {
            if (cartItem.getProduct().getid().equals(id)) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }
        items.add(item);
    }

    public void updateItem(LineItem item){
        String id = item.getProduct().getid();
        int quantity = item.getQuantity();
        for (LineItem cartItem : items) {
            if (cartItem.getProduct().getid().equals(id)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
    }

    public void removeItem(LineItem item) {
        String id = item.getProduct().getid();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getid().equals(id)) {
                items.remove(i);
                return;
            }
        }
    }
    public double getTotalAmount() {
        double total = 0;
        for (LineItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public String getTotalAmountCurrencyFormat() {
        java.text.NumberFormat currency = java.text.NumberFormat.getCurrencyInstance(java.util.Locale.US);
        return currency.format(this.getTotalAmount());
    }
}