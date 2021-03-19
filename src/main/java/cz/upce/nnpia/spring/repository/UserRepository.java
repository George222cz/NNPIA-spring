package cz.upce.nnpia.spring.repository;

import cz.upce.nnpia.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
