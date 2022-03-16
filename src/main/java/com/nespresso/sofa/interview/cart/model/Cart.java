package com.nespresso.sofa.interview.cart.model;

import static java.util.UUID.randomUUID;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Cart implements Serializable {

    private final UUID id;
    private Map<String, Integer> products= new HashMap<>();
    public Cart() {
        this(randomUUID());
        this.products=new HashMap<>();
    }

    public Cart(UUID id) {

        this.id = id;
    }

    public Cart(Map<String, Integer> products) {
        this.id = randomUUID();
        this.products=products;
    }

    public UUID getId() {
        return id;
    }


    public void setProductsWithoutParms(Map<String, Integer> products) {
        this.products = products;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public String toString(){
        return "Cart {id: " + getId() + ", products: "+products+"}";
    }


}
