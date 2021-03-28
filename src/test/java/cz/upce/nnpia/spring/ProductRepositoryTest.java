package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.datafactory.ProductTestDataFactory;
import cz.upce.nnpia.spring.datafactory.SupplierTestDataFactory;
import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({ProductTestDataFactory.class, SupplierTestDataFactory.class})
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductTestDataFactory productTestDataFactory;

	@Test
	void saveProductTest() {
		productTestDataFactory.saveProduct("MyProduct");

		List<Product> all = productRepository.findAll();
		Assertions.assertThat(all.size()).isEqualTo(1);
	}

	@Test
	void findProductByRatingTest(){
		productTestDataFactory.saveProduct("MyProduct1");
		productTestDataFactory.saveProduct("MyProduct2");
		productTestDataFactory.saveProduct("MyProduct3");

		List<Product> products = productRepository.findProductsByRating(5);
		for (Product p: products) {
			Assertions.assertThat(p.getRating()).isNotEqualTo(5);
		}
	}

	@Test
	void deleteProductTest() {
		Product product = productTestDataFactory.saveProduct("MyProduct");

		productRepository.delete(product);
		List<Product> all = productRepository.findAll();
		Assertions.assertThat(all.size()).isEqualTo(0);
	}

}
