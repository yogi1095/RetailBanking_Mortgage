package com.bank.retailbanking.service;

import java.util.List;

import com.bank.retailbanking.dto.AccountResponseDto;
import com.bank.retailbanking.entity.Account;
import com.bank.retailbanking.exception.MortgageNotFoundException;

/**
 * This service is having all the methods of the account.
 */
public interface AccountService {

	/**
	 * This method is used get all accountNumbers.
	 * 
	 * @param accountNumber. This is the account number of the current customer.
	 * @return This has the return type of AccountResponseDto.This returns list of
	 *         accountNumbers and String of result along with the statusCode.
	 */
	AccountResponseDto getAllAccountNumber(Long accountNumber);

	List<Account> getAccounts(Integer customerId);
	
	Account getMortgageAccount(Integer customerId) throws MortgageNotFoundException;

}