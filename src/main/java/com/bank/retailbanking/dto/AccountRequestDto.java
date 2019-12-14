package com.bank.retailbanking.dto;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class AccountRequestDto {
	private Integer customerId;
	private Double balance;
}
