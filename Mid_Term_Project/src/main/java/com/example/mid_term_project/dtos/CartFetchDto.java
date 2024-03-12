package com.example.mid_term_project.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartFetchDto {
    private Long id;
    private Long userId;
    private String date;
    private String products;
    private String category;

}
