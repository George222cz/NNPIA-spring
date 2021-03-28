package cz.upce.nnpia.spring.repository;

import cz.upce.nnpia.spring.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
