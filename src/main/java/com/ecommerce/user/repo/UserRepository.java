package com.ecommerce.user.repo;

import com.ecommerce.user.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   Optional<User> findByEmail(String emailAddress);

   boolean existsByEmail(String emailAddress);

}
