package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.datafactory.OrderTestDataFactory;
import cz.upce.nnpia.spring.entity.Order;
import cz.upce.nnpia.spring.entity.StateEnum;
import cz.upce.nnpia.spring.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(OrderTestDataFactory.class)
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderTestDataFactory orderTestDataFactory;

	@Test
	void findByIdTest() {
		Order order = orderTestDataFactory.saveOrder(StateEnum.NEW);

		Optional<Order> byId = orderRepository.findById(order.getId());
		Assertions.assertThat(byId.isPresent()).isTrue();
	}

	@Test
	void findOrdersByStateDeliveredTest() {
		orderTestDataFactory.saveOrder(StateEnum.NEW);
		orderTestDataFactory.saveOrder(StateEnum.DELIVERED);
		orderTestDataFactory.saveOrder(StateEnum.DELIVERED);

		List<Order> ordersByState = orderRepository.findOrdersByStateDelivered();
		Assertions.assertThat(ordersByState.size()).isEqualTo(2);
	}

}
