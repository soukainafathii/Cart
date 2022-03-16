package com.nespresso.sofa.interview.cart.services;
import java.util.HashMap;
import java.util.Map;
import com.nespresso.sofa.interview.cart.model.Cart;
/**
 * If one or more product with code "1000" is purchase, ONE product with code 9000 is offer
 * For each 10 products purchased a gift with code 7000 is offer.
 */
public class PromotionEngine {

    public Cart apply(Cart cart) {
        Map<String, Integer> products;
        products= new HashMap<>(cart.getProducts());
        String GIFT = "7000";

        int numOfProds = 0;
        for (int value: products.values()) {
            numOfProds += value;
        }

        if(products.get("1000")!=null){
            products.put("9000", 1);
        }else{
            products.remove(GIFT);
        }

        if(numOfProds >= 10){
            int quantity = numOfProds / 10;
            products.put(GIFT, quantity);
        }


        if(numOfProds < 10 && products.get(GIFT)!=null){
            products.remove(GIFT);
        }

        cart.setProductsWithoutParms(products);
        return cart;
    }
}