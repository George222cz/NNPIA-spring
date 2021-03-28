package cz.upce.nnpia.spring.datafactory;

import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.entity.Supplier;
import cz.upce.nnpia.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTestDataFactory {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierTestDataFactory supplierTestDataFactory;

    public Product saveProduct(String productName) {
        Product product = new Product();
        product.setName(productName);
        saveProductWithDefaultSupplier(product);
        return product;
    }

    public void saveProduct(Product product) {
        if(product.getName()==null) product.setName("Test name");
        if(product.getDescription()==null) product.setDescription("Test description");
        saveProductWithDefaultSupplier(product);
    }

    private void saveProductWithDefaultSupplier(Product product) {
        Supplier testSupplier = supplierTestDataFactory.saveSupplier();
        product.setSupplier(testSupplier);
        productRepository.save(product);
    }

}
