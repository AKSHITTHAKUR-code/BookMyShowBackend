package com.codeworld.BookMyShowBackend.repository;


import com.codeworld.BookMyShowBackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    boolean existsByEmail(String Email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsBy(Long id);

    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByPhoneNumber( String phoneNumber);
}
