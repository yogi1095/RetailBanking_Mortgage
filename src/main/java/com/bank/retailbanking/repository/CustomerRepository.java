package com.bank.retailbanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByUserNameAndPassword(String userName,String password);
	Optional<Customer> findByEmailId(String emailId);
	Optional<Customer> findByUserName(String userName);
	

}