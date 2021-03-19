package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	void saveProductTest() {
		Product product = new Product();
		product.setName("MyProduct");
		productRepository.save(product);

		List<Product> all = productRepository.findAll();
		Assertions.assertThat(all.size()).isEqualTo(1);
	}

	@Test
	void findProductByRatingTest(){
		Product product1 = new Product();
		product1.setName("MyProduct1");
		product1.setRating(5);
		productRepository.save(product1);

		Product product2 = new Product();
		product2.setName("MyProduct2");
		product2.setRating(4);
		productRepository.save(product2);

		Product product3 = new Product();
		product3.setName("MyProduct3");
		product3.setRating(5);
		productRepository.save(product3);

		List<Product> products = productRepository.findProductsByRating(5);
		for (Product p: products) {
			Assertions.assertThat(p.getRating()).isNotEqualTo(5);
		}
	}

	@Test
	void deleteProductTest() {
		Product product = new Product();
		product.setName("MyProduct");
		productRepository.save(product);

		productRepository.delete(product);
		List<Product> all = productRepository.findAll();
		Assertions.assertThat(all.size()).isEqualTo(0);
	}

}
