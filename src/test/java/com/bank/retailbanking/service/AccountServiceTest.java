package com.bank.retailbanking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.AccountSummaryResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.entity.Transaction;
import com.bank.retailbanking.exception.MortgageNotFoundException;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AccountServiceTest {

	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	@Mock
	TransactionRepository transactionRepository;

	AccountSummaryResponseDto accountSummaryResponseDto = null;
	Customer customer = null;
	Account account = null;
	Transaction transaction = null;
	List<Transaction> transactionsList = new ArrayList<>();

	@Test
	public void testGetMortgageAccountPositive() throws MortgageNotFoundException {
		
		customer = new Customer();
		customer.setCustomerId(1);
		customer.setAddress("Bng");
		customer.setDateOfBirth(LocalDate.parse("1996-12-03"));
		customer.setEmailId("yoga@gmail.com");
		customer.setFirstName("yoga");
		customer.setLastName("reddy");
		customer.setMobileNo(9491777925L);
		customer.setUserName("HI");
		customer.setPassword("Hello");
		
		account = new Account();
		account.setAccountNumber(60987652L);
		account.setAccountStatus("open");
		account.setAccountType(Constant.MORTGAGE);
		account.setBalance((double) 3489);
		account.setCustomer(customer);
		
		Mockito.when(accountRepository.findByCustomerAndAccountType(Mockito.any(),Mockito.any())).thenReturn(Optional.of(new Account()));
		Account account =accountServiceImpl.getMortgageAccount(1);
		assertNotNull(account);
	}
	
	@Before
	public void setup() {

		customer = new Customer();
		customer.setCustomerId(1);
		customer.setAddress("Bng");
		customer.setDateOfBirth(LocalDate.parse("2019-12-03"));
		customer.setEmailId("yoga@gmail.com");
		customer.setFirstName("yoga");
		customer.setLastName("reddy");
		customer.setMobileNo(9491777925L);
		customer.setUserName("HI");
		customer.setPassword("Hello");

		account = new Account();
		account.setAccountNumber(60987651L);
		account.setAccountStatus("Active");
		account.setAccountType("Savings");
		account.setBalance((double) 3489);
		account.setCustomer(customer);

		transaction = new Transaction();
		transaction.setAccount(account);
		transaction.setTransactionAmount(3489.00);
		transaction.setTransactionDate(LocalDate.parse("2019-12-03"));
		transaction.setTransactionDescription("use");
		transaction.setTransactionId(1);
		transaction.setTransactionType("credit");
		transactionsList.add(transaction);
	}

	@Test
	public void findAllAccountNumbersNegative() {
		account = new Account();
		account.setAccountNumber(1111111L);
		List<Account> accounts = new ArrayList<Account>();
		Mockito.when(accountRepository.findAllByAccountNumberNot(account.getAccountNumber())).thenReturn(accounts);
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setTransferAccounts(null);
		accountResponseDto.setMessage(Constant.ACCOUNT_NOT_FOUND);
		accountResponseDto.setStatusCode(Constant.ACCOUNT_NOT_FOUND_CODE);
		AccountResponseDto accountResponseDto1 = accountServiceImpl.getAllAccountNumber(account.getAccountNumber());
		assertNull(accountResponseDto1.getTransferAccounts());
	}

	/**
	 * This method is used to test accountSummary and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */
	@Test
	public void testAccountSummary() {
		Mockito.when(customerRepository.findById(5)).thenReturn(Optional.of(customer));
		Mockito.when(accountRepository.findByCustomer(customer)).thenReturn(new ArrayList<Account>());
		Mockito.when(transactionRepository.findTop5ByAccountOrderByTransactionIdDesc(account))
				.thenReturn(transactionsList);

		List<Account> accountSummaryResponseDto = accountServiceImpl.getAccounts(5);
		Assert.assertNotNull(accountSummaryResponseDto);
	}
	

	
	@Test(expected = MortgageNotFoundException.class)
	public void testGetMortgageAccount() throws MortgageNotFoundException {
		
		customer = new Customer();
		customer.setCustomerId(1);
		customer.setAddress("Bng");
		customer.setDateOfBirth(LocalDate.parse("2019-12-03"));
		customer.setEmailId("yoga@gmail.com");
		customer.setFirstName("yoga");
		customer.setLastName("reddy");
		customer.setMobileNo(9491777925L);
		customer.setUserName("HI");
		customer.setPassword("Hello");
		
		account = new Account();
		account.setAccountNumber(60987651L);
		account.setAccountStatus("open");
		account.setAccountType(Constant.MORTGAGE);
		account.setBalance((double) 3489);
		account.setCustomer(customer);
		Mockito.when(accountRepository.findByCustomerAndAccountType(customer,"Mortgage")).thenReturn(Optional.of(account));
		Account account =accountServiceImpl.getMortgageAccount(1);
		assertNotNull(account);
	}

}
