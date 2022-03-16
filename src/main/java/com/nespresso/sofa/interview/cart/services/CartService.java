package com.nespresso.sofa.interview.cart.services;
import java.util.UUID;
import com.nespresso.sofa.interview.cart.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
public class CartService {

    @Autowired
    private PromotionEngine promotionEngine;
    @Autowired
    private CartStorage cartStorage;

    private Cart cart;
//    @Autowired
//    public CartService(PromotionEngine promotionEngine, CartStorage cartStorage) {
//        this.promotionEngine = promotionEngine;
//        this.cartStorage = cartStorage;
//    }
    /**
     * Add a quantity of a product to the cart and store the cart
     *
     * @param cartId
     *     The cart ID
     * @param productCode
     *     The product code
     * @param quantity
     *     Quantity must be added
     * @return True if card has been modified
     */
    public boolean add(UUID cartId, String productCode, int quantity) {
        boolean res = false;
        final Cart cart = cartStorage.loadCart(cartId);
        if (productCode != null && quantity > 0) {
            res = true;
            if (cart.getProducts().get(productCode)!=null) {
                int newQuantity = cart.getProducts().get(productCode) + quantity;
                (cart).getProducts().replace(productCode, newQuantity);
            } else {
                cart.getProducts().put(productCode,quantity);
            }
            cartStorage.saveCart(cart);
        }
        return res;
    }

    /**
     * Set a quantity of a product to the cart and store the cart
     *
     * @param cartId
     *     The cart ID
     * @param productCode
     *     The product code
     * @param quantity
     *     The new quantity
     * @return True if card has been modified
     */

    public boolean set(UUID cartId, String productCode, int quantity) {

        boolean res = true;
        final Cart cart = cartStorage.loadCart(cartId);

        if (quantity <= 0) {
            cart.getProducts().remove(productCode);
        }
        else if (productCode != null) {
            if (cart.getProducts().get(productCode)!=null && cart.getProducts().get(productCode) == quantity) {
                res = false;
            }
            else {
                cart.getProducts().put(productCode,quantity);
            }
            cartStorage.saveCart(cart);
        }
        return res;
    }
    /**
     * Return the card with the corresponding ID
     *
     * @param cartId
     * @return
     */
    public Cart get(UUID cartId) {
        return cartStorage.loadCart(cartId);
    }



}