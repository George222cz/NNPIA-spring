package cz.upce.nnpia.spring.datafactory;

import cz.upce.nnpia.spring.entity.Order;
import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.entity.StateEnum;
import cz.upce.nnpia.spring.entity.Supplier;
import cz.upce.nnpia.spring.repository.OrderRepository;
import cz.upce.nnpia.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderTestDataFactory {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(StateEnum state) {
        Order order = new Order();
        order.setState(state);
        orderRepository.save(order);
        return order;
    }

    public void saveOrder(Order order) {
        if(order.getState()==null) order.setState(StateEnum.NEW);
        orderRepository.save(order);
    }

}
