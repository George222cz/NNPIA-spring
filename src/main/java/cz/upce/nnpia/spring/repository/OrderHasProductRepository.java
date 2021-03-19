package cz.upce.nnpia.spring.repository;

import cz.upce.nnpia.spring.entity.OrderHasProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, Long> {
}
