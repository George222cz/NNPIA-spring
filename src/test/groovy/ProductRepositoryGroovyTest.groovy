package cz.upce.nnpia.spring;

import cz.upce.nnpia.spring.datafactory.ProductTestDataFactory
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
@Import([ProductTestDataFactory.class, SupplierTestDataFactory.class])
class ProductRepositoryGroovyTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductTestDataFactory productTestDataFactory;

    @Test
    void saveProductTest() {
        Product testProduct = new Product(name: "MyProduct");
        productTestDataFactory.saveProduct(testProduct);
        List<Product> all = productRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(1);

        def readFromDb = productRepository.findById(testProduct.getId()).get();
        Assertions.assertThat(readFromDb.getName()).isEqualTo("MyProduct");
        Assertions.assertThat(readFromDb.getDescription()).isEqualTo("Test description");
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
        Product testProduct = new Product(name: "MyProduct");
        productTestDataFactory.saveProduct(testProduct);

        productRepository.delete(testProduct);
        List<Product> all = productRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(0);
    }

}
