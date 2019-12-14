package com.bank.retailbanking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.AccountRequestDto;
import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.dto.TransferAccountDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.repository.AccountRepository;
import com.bank.retailbanking.repository.CustomerRepository;
import com.bank.retailbanking.repository.TransactionRepository;

/**
 * This service is having all the implementations of methods of the accounts.
 * 
 * @author PriyaDharshini S
 */
@Service
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	/**
	 * This will inject all the methods in the accountRepository.
	 */
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * This API is used to findAllAccountNumbers which will find all the
	 * accountNumbers except the current customer's accountNumber
	 * 
	 * @pathVariable accountNumber.This is the accountNumber of the current
	 *               customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	@Override
	public AccountResponseDto getAllAccountNumber(Long accountNumber) {
		List<Account> accounts = accountRepository.findAllByAccountNumberNot(accountNumber);
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		List<TransferAccountDto> transferAccountDtos = new ArrayList<>();
		if (accounts.isEmpty()) {
			accountResponseDto.setMessage(Constant.ACCOUNT_NOT_FOUND);
			accountResponseDto.setStatusCode(Constant.ACCOUNT_NOT_FOUND_CODE);
			accountResponseDto.setTransferAccounts(null);
		} else {
			for (Account account : accounts) {
				TransferAccountDto transferAccountDto = new TransferAccountDto();
				Optional<Customer> customer = customerRepository.findById(account.getCustomer().getCustomerId());
				if (customer.isPresent()) {
					transferAccountDto.setAccountNumber(account.getAccountNumber());
					transferAccountDto.setUserName(customer.get().getUserName());
					transferAccountDtos.add(transferAccountDto);
				}
			}
			accountResponseDto.setTransferAccounts(transferAccountDtos);
			accountResponseDto.setMessage(Constant.SUCCESS);
			accountResponseDto.setStatusCode(Constant.ACCEPTED);
		}
		return accountResponseDto;
	}

	/**
	 * This API is used to get accountSummary which will find all the accountNumbers
	 * and five transactions.
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */
	@Override
	public List<Account> getAccounts(Integer customerId) {
		logger.info("to get account summary");
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		return accountRepository.findByCustomer(customer);
	}

	/**
	 * This API is used to create account
	 * 
	 * @pathVariable customerId.This is the customerId of the customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         accountSummary and String of result along with the statusCode.
	 */
	@Override
	public Account createAccount(AccountRequestDto accountRequestDto) {
		Account account = new Account();
		Optional<Customer> customer = customerRepository.findById(accountRequestDto.getCustomerId());
		account.setCustomer(customer.get());
		account.setBalance(accountRequestDto.getBalance());
		account.setAccountType(Constant.ACCOUNT_TYPE);
		account.setAccountStatus(Constant.ACCOUNT_STATUS_ACTIVE);
		accountRepository.save(account);
		return account;
	}

	@Override
	public List<Account> searchAccounts(Long accountNumber) {
		List<Account> accounts = accountRepository.findAll();
		accounts = accounts.stream().filter(
				account -> account.getAccountNumber().toString().contains(accountNumber.toString()))
				.collect(Collectors.toList());
		return accounts;
	}

}