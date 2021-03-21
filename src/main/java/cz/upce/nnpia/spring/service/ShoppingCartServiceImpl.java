package cz.upce.nnpia.spring.service;

import cz.upce.nnpia.spring.entity.Order;
import cz.upce.nnpia.spring.entity.OrderHasProduct;
import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.entity.StateEnum;
import cz.upce.nnpia.spring.repository.OrderHasProductRepository;
import cz.upce.nnpia.spring.repository.OrderRepository;
import cz.upce.nnpia.spring.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SessionScope
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private Map<Product,Integer> cart;

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderHasProductRepository orderHasProductRepository;

    public ShoppingCartServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, OrderHasProductRepository orderHasProductRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.orderHasProductRepository = orderHasProductRepository;
        cart = new HashMap<>();
    }

    @Override
    public void add(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.containsKey(product)){
            cart.replace(product,cart.get(product)+1);
        }else{
            cart.put(product,1);
        }
    }

    @Override
    public void remove(Long id) {
        Product product = productRepository.findById(id).orElseThrow(NoSuchElementException::new);
        if(cart.containsKey(product)){
            if(cart.get(product) > 1){
                cart.replace(product, cart.get(product) -1);
            }else{
                cart.remove(product);
            }
        }

    }

    @Override
    public Map<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public void checkout() {
        Order order = new Order();
        order.setState(StateEnum.NEW);
        order.setOrderDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        orderRepository.save(order);

        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            OrderHasProduct orderHasProduct = new OrderHasProduct();
            orderHasProduct.setOrder(order);
            orderHasProduct.setProduct(entry.getKey());
            orderHasProduct.setAmount(entry.getValue());
            orderHasProductRepository.save(orderHasProduct);
        }

        cart.clear();
    }
}
