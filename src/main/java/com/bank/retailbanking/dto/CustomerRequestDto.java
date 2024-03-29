package com.bank.retailbanking.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRequestDto {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Long mobileNo;
	private String emailId;
	private String address;
	private String userName;
	private String password;
	private Double salary;
}
