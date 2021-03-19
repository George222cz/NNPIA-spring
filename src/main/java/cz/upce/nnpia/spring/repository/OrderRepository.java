package cz.upce.nnpia.spring.repository;

import cz.upce.nnpia.spring.entity.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "orderHasProducts")
    Optional<Order> findById(Long id);

    @EntityGraph(attributePaths = {"orderHasProducts"})
    @Query("select o from order_form o where o.state = cz.upce.nnpia.spring.entity.StateEnum.DELIVERED")
    List<Order> findOrdersByStateDelivered();
}
