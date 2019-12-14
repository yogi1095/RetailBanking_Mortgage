package com.bank.retailbanking.service;

import java.util.List;

import com.bank.retailbanking.dto.FundTransferRequestDto;
import com.bank.retailbanking.dto.FundTransferResponseDto;
import com.bank.retailbanking.dto.TransactionRequestDto;
import com.bank.retailbanking.entity.Transaction;

public interface TransactionService {

	FundTransferResponseDto fundTransfer(FundTransferRequestDto fundTransferRequestDto);
	
	List<Transaction> getTransactions(TransactionRequestDto transactionRequestDto);
	

}
