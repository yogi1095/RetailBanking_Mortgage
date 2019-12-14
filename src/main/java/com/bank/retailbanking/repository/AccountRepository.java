package com.bank.retailbanking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findAllByAccountNumberNot(Long accountNumber);

	Optional<Account> findByAccountNumber(Long accountNumber);

	List<Account> findByCustomer(Customer customerId);
	
	Optional<Account> findByCustomerAndAccountType(Customer customerId,String accountType);
}
