package cz.upce.nnpia.spring.datafactory;

import cz.upce.nnpia.spring.entity.Product;
import cz.upce.nnpia.spring.entity.Supplier;
import cz.upce.nnpia.spring.repository.ProductRepository;
import cz.upce.nnpia.spring.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierTestDataFactory {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier saveSupplier() {
        Supplier testSupplier = new Supplier();
        testSupplier.setName("Test supplier");
        supplierRepository.save(testSupplier);
        return testSupplier;
    }
}
