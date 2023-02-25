package com.revature.service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.model.Transfer;
import com.revature.repository.BankAccountRepository;
import com.revature.repository.TransferRepository;

@Service
public class TransferService {
	private final TransferRepository transferRepository;
  public final BankAccountRepository bankAccountRepository;

	public TransferService(TransferRepository transferRepository, BankAccountRepository bankAccountRepository) {
		this.transferRepository = transferRepository;
    this.bankAccountRepository = bankAccountRepository;
	}

  public List<Transfer> getTransfers() {
    return transferRepository.findAll();
  }

  public ResponseEntity<String> addNewTransfer(Transfer transfer) {
    if (transfer.getAmount() == null) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }
    if (transfer.getAmount().compareTo(new BigDecimal(0)) <= 0) {
      return new ResponseEntity<>("You cannot send that amount of money!", HttpStatus.BAD_REQUEST);
    }

    //Uses the bank account repository to determine if a transfer is valid (i.e. would be connected to an actual account)
    
    if(!bankAccountRepository.findById(transfer.getAccountId()).isPresent()) {
      return ResponseEntity.status(403).body("That transfer is not linked to any account!");
    }
  

    //TODO: Validate that the transfer that is being sent up would not point at an account that doesn't exist.

    try {
      transferRepository.save(transfer);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Something went wrong while attempting to save your transfer.");
    }
    
    String actionWord = transfer.getIsDeposit()? "Deposit" : "Withdraw";
    return new ResponseEntity<>(actionWord + " ($" + transfer.getAmount() + ") was successful.", HttpStatus.OK);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid){
    List<Transfer> body;
    try {
      body = transferRepository.findByAccountId(accountid);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
    if(body.isEmpty()) return ResponseEntity.status(404).body(body);
    return ResponseEntity.status(200).body(body);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid, boolean isDeposit){
    List<Transfer> body = transferRepository.findByAccountIdAndIsDeposit(accountid, isDeposit);
    if(body.isEmpty()) return ResponseEntity.status(404).body(body);
    return ResponseEntity.status(200).body(body);
  }

  public ResponseEntity<String> sendMoneyBetweenTwoAccounts(Long fromAccountId, Long toAccountId, BigDecimal amount){
    //TODO: Check here that there is enough money in fromAccountId to send
    //TODO: Validate that the account owner is the one sending the money.
    if (amount == null) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }
    if (amount.compareTo(new BigDecimal(0)) <= 0) {
      return new ResponseEntity<>("You cannot send that amount of money!", HttpStatus.BAD_REQUEST);
    }

    if(fromAccountId == toAccountId){
      return new ResponseEntity<>("You cannot transfer money from an account to itself!", HttpStatus.BAD_REQUEST);
    }
    Transfer from = new Transfer(
      fromAccountId, 
      amount, 
      false, 
      "Account-to-Account transfer: " + fromAccountId + " to " + toAccountId + ": $" + amount);
    Transfer to = new Transfer(
      toAccountId, 
      amount, 
      true, 
      "Account-to-Account transfer: " + fromAccountId + " to " + toAccountId + ": $" + amount);
      transferRepository.save(from);
      transferRepository.save(to);
    return new ResponseEntity<>("Successfully transferred $" + amount + " from account #" + fromAccountId + " to #" + toAccountId, HttpStatus.OK);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountAndTime(Long accId, int year, int month){
    List<Transfer> transfers;
    try{
      transfers = transferRepository.getUsingAccountIdAndMonth(accId, year, month);
    } catch (Exception e){
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
    if(transfers.isEmpty()) return ResponseEntity.status(404).body(transfers);
    return ResponseEntity.status(200).body(transfers);
  }
}