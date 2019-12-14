package com.bank.retailbanking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.AccountRequestDto;
import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.service.AccountService;

/**
 * This Controller is having the account related functionalities. This
 * Controller will call the accountService in which the implementations are
 * done.
 * 
 * @API It has a method findAllAccountNumbers which will find all the
 *      accountNumbers except the current customer's accountNumber.
 * @author PriyaDharshini S
 */
@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	/**
	 * This will inject all the implementations in the accountService.
	 */
	@Autowired
	AccountService accountService;

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	/**
	 * This API is used to findAllAccountNumbers which will find all the
	 * accountNumbers except the current customer's accountNumber
	 * 
	 * @pathVariable accountNumber.This is the accountNumber of the current
	 *               customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	@GetMapping("/{accountNumber}")
	public AccountResponseDto findAllAccountNumbers(@PathVariable Long accountNumber) {
		logger.info("Entering into findAllAccountNumbers method");
		return accountService.getAllAccountNumber(accountNumber);

	}

	/**
	 * This API is used to find accountSummary which will find last five
	 * transactions and account number and name
	 * 
	 * @pathVariable customerId.This is the customerId of the current customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         last five transactions and accountNumbers and String of result along
	 *         with the statusCode.
	 */

	@GetMapping(value = "/accountsummary/{customerId}")
	public ResponseEntity<List<Account>> getAccounts(@PathVariable Integer customerId) {
		logger.info("fetching Account Summary..");
		return ResponseEntity.ok().body(accountService.getAccounts(customerId));

	}
	/**
	 * This API is used to find accountSummary which will find last five
	 * transactions and account number and name
	 * 
	 * @pathVariable customerId.This is the customerId of the current customer.
	 * @return This has the return type of AccountSummaryResponseDto.This returns
	 *         last five transactions and accountNumbers and String of result along
	 *         with the statusCode.
	 */
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody AccountRequestDto accountRequestDto){
		return ResponseEntity.ok().body(accountService.createAccount(accountRequestDto));
	}
	
	@GetMapping(value = "/search/{accountNumber}")
	public ResponseEntity<List<Account>> searchAccount(@PathVariable("accountNumber") Long accountNumber){
		return ResponseEntity.ok().body(accountService.searchAccounts(accountNumber));
	}

}