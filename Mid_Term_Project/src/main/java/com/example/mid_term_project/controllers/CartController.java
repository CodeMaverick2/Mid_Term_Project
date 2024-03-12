package com.example.mid_term_project.controllers;
import com.example.mid_term_project.services.CartService;
import com.example.mid_term_project.models.Cart;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/carts")
public class CartController {
    private CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @GetMapping(value = "/")
    public List<Cart> getCarts() {
        return cartService.getCarts();
    }
    @GetMapping(value = "/limited/{limit}")
    public List<Cart> getLimitedCarts(@PathVariable int limit) {
        return cartService.getLimitedCarts(limit);
    }
    @GetMapping(value = "/sorted")
    public List<Cart> getSortedCarts(@RequestParam("order") String sort) {
        return cartService.getSortedCarts(sort);
    }
    @GetMapping(value = "/carts/{id}/")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
        return "Cart deleted successfully";
    }

    @PostMapping("/add")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @PutMapping("/update")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }


}
