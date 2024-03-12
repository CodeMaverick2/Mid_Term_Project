package com.example.mid_term_project.services;

import com.example.mid_term_project.dtos.CartFetchDto;
import com.example.mid_term_project.models.Cart;
import org.springframework.core.ParameterizedTypeReference;
import com.example.mid_term_project.models.Category;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartServiceimpl implements CartService {
    private final String url = "https://fakestoreapi.com/carts";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Cart> getCarts() {

        List<CartFetchDto> cartFetchDtos = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartFetchDto>>() {}).getBody();

        assert cartFetchDtos != null;

        return cartFetchDtos.stream().map(this::mapToCart).toList();
    }

    private Cart mapToCart(CartFetchDto cartFetchDto) {
        Category category = new Category(1L, cartFetchDto.getCategory());
        category.setName(cartFetchDto.getCategory());
        return new Cart(cartFetchDto.getId(), cartFetchDto.getUserId(), cartFetchDto.getDate(), cartFetchDto.getProducts(), category);
    }
    public Cart getCart(Long id) {

        CartFetchDto cartFetchDto= restTemplate.getForObject(url + "/" + id, CartFetchDto.class);

        assert cartFetchDto != null;

        return mapToCart(cartFetchDto);
    }


    public Cart addCart(Cart cart) {

//        Add cart to the fakestoreapi
        CartFetchDto cartFetchDto = new CartFetchDto();
        cartFetchDto.setCategory(cart.getCategory().getName());
        cartFetchDto.setDate(cart.getDate());
        cartFetchDto.setUserId(cart.getUserId());
        cartFetchDto.setProducts(cart.getProducts());

        CartFetchDto response = restTemplate.postForObject(url, cartFetchDto, CartFetchDto.class);

        assert response != null;

        return mapToCart(response);
    }
    @Override
    public Cart updateCart(Cart cart) {
//        Update cart to the fakestoreapi
        CartFetchDto cartFetchDto = new CartFetchDto();
        cartFetchDto.setId(cart.getId());
        cartFetchDto.setCategory(cart.getCategory().getName());
        cartFetchDto.setDate(cart.getDate());
        cartFetchDto.setUserId(cart.getUserId());
        cartFetchDto.setProducts(cart.getProducts());

        restTemplate.put(url + "/" + cart.getId(), cartFetchDto);

        return cart;
    }

    public void deleteCart(Long id) {
        restTemplate.delete(url + "/" + id);
    }
    public List<Cart> getLimitedCarts(int limit) {
        List<CartFetchDto> cartFetchDtos = restTemplate.exchange(
                url + "?limit=" + limit,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartFetchDto>>() {}).getBody();

        assert cartFetchDtos != null;

        return cartFetchDtos.stream().map(this::mapToCart).toList();

    }
    public List<Cart> getSortedCarts(String sort) {
        List<CartFetchDto> cartFetchDtos = restTemplate.exchange(
                url + "?sort=" + sort,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartFetchDto>>() {}).getBody();

        assert cartFetchDtos != null;

        return cartFetchDtos.stream().map(this::mapToCart).toList();

    }

}
