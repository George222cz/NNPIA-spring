package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.repository.ProductRepository;
import cz.upce.nnpia.spring.service.ShoppingCartService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
class ShoppingCartTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Test
	void addOneProductToShoppingCartTest() {
		Product product = new Product();
		product.setName("MyProduct");
		productRepository.save(product);
		List<Product> all = productRepository.findAll();

		Long productId = all.get(0).getId();

		shoppingCartService.add(productId);

		Assertions.assertThat(shoppingCartService.getCart().size()).isEqualTo(1);

		Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isTrue();

		Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);

		shoppingCartService.add(productId);
		Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

		shoppingCartService.add(productId);
		Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(3);

		shoppingCartService.remove(productId);
		Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(2);

		shoppingCartService.remove(productId);
		Assertions.assertThat(shoppingCartService.getCart().get(all.get(0))).isEqualTo(1);

		shoppingCartService.remove(productId);
		Assertions.assertThat(shoppingCartService.getCart().containsKey(all.get(0))).isFalse();
	}



}
