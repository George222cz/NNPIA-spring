package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.datafactory.Creator;
import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.repository.ProductRepository;
import cz.upce.nnpia.spring.service.ShoppingCartService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(Creator.class)
class ShoppingCartTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private Creator creator;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Test
	void addOneProductToShoppingCartTest() {
		creator.save(new Product());
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
