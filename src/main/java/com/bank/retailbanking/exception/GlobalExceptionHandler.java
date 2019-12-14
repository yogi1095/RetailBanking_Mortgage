package com.bank.retailbanking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.retailbanking.constants.Constant;
import com.bank.retailbanking.dto.CustomerResponseDto;
import com.bank.retailbanking.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNameAlreadyExistException.class)
	public ResponseEntity<CustomerResponseDto> userNameAlreadyExistException() {

		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setStatusCode(Constant.USER_NOT_ACCEPTABLE);
		customerResponseDto.setMessage(Constant.USER_NAME_ALREADY_EXIST);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(customerResponseDto);
	}

	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<CustomerResponseDto> emailAlreadyExistException() {

		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setStatusCode(Constant.EMAIL_NOT_ACCEPTABLE);
		customerResponseDto.setMessage(Constant.EMAIL_ALREADY_EXISTS);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(customerResponseDto);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDto> userNotFoundException() {

		ErrorDto errorDto = new ErrorDto();
		errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorDto.setMessage(Constant.USER_NOT_FOUND);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDto);
	}

}
