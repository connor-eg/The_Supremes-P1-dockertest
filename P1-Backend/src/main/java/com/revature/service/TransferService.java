package com.revature.service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.model.Transfer;
import com.revature.repository.TransferRepository;

@Service
public class TransferService {
	private final TransferRepository transferRepository;

	public TransferService(TransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

  public List<Transfer> getTransfers() {
    return transferRepository.findAll();
  }

  public ResponseEntity<String> addNewTransfer(Transfer transfer) {
    if (transfer.gettraamount() == null) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }
    if (transfer.gettraamount().compareTo(new BigDecimal(0)) <= 0) {
      return new ResponseEntity<>("You cannot send that amount of money!", HttpStatus.BAD_REQUEST);
    }
    transferRepository.save(transfer);
    String actionWord = transfer.gettraisdeposit()? "Deposit" : "Withdraw";
    return new ResponseEntity<>(actionWord + " ($" + transfer.gettraamount() + ") was successful.", HttpStatus.OK);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid){
    List<Transfer> body = transferRepository.getTransfersByTraAccountId(accountid);
    if(body.isEmpty()) return ResponseEntity.status(404).body(body);
    return ResponseEntity.status(200).body(body);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid, boolean isDeposit){
    List<Transfer> body = transferRepository.getTransfersByTraAccountId(accountid, isDeposit);
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
      transfers = transferRepository.getTransfersByAccountIdAndYearAndMonth(accId, year, month);
    } catch (Exception e){
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
    if(transfers.isEmpty()) return ResponseEntity.status(404).body(transfers);
    return ResponseEntity.status(200).body(transfers);
  }
}