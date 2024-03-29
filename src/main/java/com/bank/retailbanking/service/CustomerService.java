package com.bank.retailbanking.service;

import com.bank.retailbanking.dto.CustomerRequestDto;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.EmailAlreadyExistException;
import com.bank.retailbanking.exception.UserNameAlreadyExistException;
import com.bank.retailbanking.exception.UserNotFoundException;

/**
 * This service is having all the methods of the customer.
 * 
 * @author PriyaDharshini S
 */
public interface CustomerService {

	/**
	 * This method is used authenticate the customer.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException
	 */
	Customer authenticateCustomer(LoginRequestDto loginRequestDto) throws UserNotFoundException;

	CustomerResponseDto registerCustomer(CustomerRequestDto customerRequestDto)
			throws UserNameAlreadyExistException, EmailAlreadyExistException;

}