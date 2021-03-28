package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.datafactory.Creator;
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
@Import(Creator.class)
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Creator creator;

	@Test
	void findByIdTest() {
		Order order = new Order();
		creator.save(order);
		Optional<Order> byId = orderRepository.findById(order.getId());
		Assertions.assertThat(byId.isPresent()).isTrue();
	}

	@Test
	void findOrdersByStateDeliveredTest() {
		Order order1 = new Order();
		creator.save(order1);

		Order order2 = new Order();
		order2.setState(StateEnum.DELIVERED);
		creator.save(order2);

		Order order3 = new Order();
		order3.setState(StateEnum.DELIVERED);
		creator.save(order3);

		List<Order> ordersByState = orderRepository.findOrdersByStateDelivered();
		Assertions.assertThat(ordersByState.size()).isEqualTo(2);
	}

}
