package com.bank.retailbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.retailbanking.dto.CustomerRequestDto;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.dto.LoginRequestDto;
import com.bank.retailbanking.entity.Customer;
import com.bank.retailbanking.exception.EmailAlreadyExistException;
import com.bank.retailbanking.exception.UserNameAlreadyExistException;
import com.bank.retailbanking.exception.UserNotFoundException;
import com.bank.retailbanking.service.CustomerService;

/**
 * This Controller is having the customer related functionalities. This
 * Controller will call the CustomerService in which the implementations are
 * done.
 * 
 * @API It has a method authenticateCustomer which will authenticate the user.
 * @author PriyaDharshini S
 */
@RestController
@RequestMapping("/customers")
@CrossOrigin
public class CustomerController {

	/**
	 * This will inject all the implementations in the CustomerService.
	 */
	@Autowired
	CustomerService customerService;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	/**
	 * This API has a method authenticateCustomer which will authenticate the user.
	 * 
	 * @param loginRequestDto.This is object of the class LoginRequestDto which has
	 *                             all the fields.
	 * @return This has the return type of LoginResponseDto.This returns customerId
	 *         and String of result along with the statusCode.
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginRequestDto loginRequestDto)
			throws UserNotFoundException {
		logger.info("Entering into authenticateCustomer in controller");
		return ResponseEntity.ok().body(customerService.authenticateCustomer(loginRequestDto));
	}

	/**
	 * In this method the customer fills the registration form and opens an account
	 * 
	 * @param customerRequestDto which includes firstName,lastName,dateOfbirth,
	 *                           emailId,address,mobileNo,userName,password
	 * @return customerResponseDto which includes statusCode and message
	 * @throws UserNameAlreadyExistException
	 * @throws EmailAlreadyExistException
	 */

	@PostMapping("")
	public CustomerResponseDto registerCustomer(@RequestBody CustomerRequestDto customerRequestDto)
			throws UserNameAlreadyExistException, EmailAlreadyExistException {
		logger.info("Registering customer....");
		return customerService.registerCustomer(customerRequestDto);
	}

}