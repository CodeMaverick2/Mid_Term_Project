package com.example.mid_term_project.services;
import com.example.mid_term_project.models.Cart;
import java.util.*;
public interface CartService {
    public List<Cart> getCarts();
    public Cart getCart(Long id);
    public Cart addCart(Cart cart);
    public Cart updateCart(Cart cart);
    public void deleteCart(Long id);
    public List<Cart> getLimitedCarts(int limit);
    public List<Cart> getSortedCarts(String sort);


}
