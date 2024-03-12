package com.example.mid_term_project.models;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private String date;
    private String products;
    private Category category;
    public Cart(Long id, Long userId, String date, String products, Category category) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.products = products;
        this.category = category;
    }

}
