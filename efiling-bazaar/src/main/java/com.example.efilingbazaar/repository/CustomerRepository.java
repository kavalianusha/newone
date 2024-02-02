package com.example.efilingbazaar.repository;

import com.example.efilingbazaar.entity.CustomerEntity;
import com.example.efilingbazaar.request.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    CustomerEntity findByEmail(String email);
    CustomerEntity findByOtp(String otp);
    void save(CustomerRequest otp);
    @Query("SELECT MAX(m.customerId) FROM CustomerEntity m")
    String findHighestCustomerId();
  /*  @Query("SELECT c FROM CustomerRequest c WHERE c.customerId = :customerId")
    CustomerRequest findBycustomerId(String customerId);*/
    @Query("SELECT c FROM CustomerEntity c WHERE c.customerId = :customerId")
    CustomerRequest findBycustomerId(String customerId);

    CustomerEntity findByCustomerId(String customerId);
}
