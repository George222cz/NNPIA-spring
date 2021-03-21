package cz.upce.nnpia.spring.service;

import cz.upce.nnpia.spring.entity.Product;

import java.util.Map;

public interface ShoppingCartService {

    void add(Long id);

    void remove(Long id);

    Map<Product,Integer> getCart();

    void checkout();
}
