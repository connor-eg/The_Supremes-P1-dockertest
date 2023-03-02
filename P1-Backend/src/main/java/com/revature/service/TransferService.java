package com.revature.service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.revature.model.BankAccount;
import com.revature.model.Transfer;
import com.revature.repository.BankAccountRepository;
import com.revature.repository.TransferRepository;

@Service
public class TransferService {
	private final TransferRepository transferRepository;
  public final BankAccountRepository bankAccountRepository;
  public final AuthService authService;

	public TransferService(TransferRepository transferRepository, BankAccountRepository bankAccountRepository, AuthService authService) {
		this.transferRepository = transferRepository;
    this.bankAccountRepository = bankAccountRepository;
    this.authService = authService;
	}

  @Transactional
  public ResponseEntity<String> addNewTransfer(Transfer transfer, String token) {
    if (transfer.getAmount() == null) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }
    if (transfer.getAmount().compareTo(new BigDecimal(0)) <= 0) {
      return new ResponseEntity<>("You cannot send that amount of money!", HttpStatus.BAD_REQUEST);
    }

    if (authService.verifyTransfer(token, transfer.getAccountId()) == false) {
      return new ResponseEntity<>("Session Token invalid. Please login again.", HttpStatus.BAD_REQUEST);
    }

    //Uses the bank account repository to determine if a transfer is valid (i.e. would be connected to an actual account)
    Optional<BankAccount> linkedBankAccountOptional =  bankAccountRepository.findById(transfer.getAccountId());
    if(!linkedBankAccountOptional.isPresent()) {
      return ResponseEntity.status(403).body("That transfer is not linked to any account!");
    }

    BankAccount linkedBankAccount = linkedBankAccountOptional.get();

    //Now that we know that the account ID points at an actual accout, validate that it has enough funds
    // for a withdraw of the requested amount (if the transfer requested is for a withdraw)
    if(!transfer.getIsDeposit()){
      if(linkedBankAccount.getBalance().compareTo(transfer.getAmount()) < 0){
        return ResponseEntity.status(403).body("There aren't enough funds in that account to make that withdrawal!");
      }
    }

    //If all of our checks passed, we are now ready to post the transfer and update the account with new info.
    try {
      if(transfer.getIsDeposit()) {
        linkedBankAccount.setBalance(linkedBankAccount.getBalance().add(transfer.getAmount()));
      } else {
        linkedBankAccount.setBalance(linkedBankAccount.getBalance().subtract(transfer.getAmount()));
      }
      bankAccountRepository.updateBalance(linkedBankAccount.getId(), linkedBankAccount.getBalance());
      transferRepository.save(transfer);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Something went wrong while attempting to save your transfer.");
    }
    
    String actionWord = transfer.getIsDeposit()? "Deposit" : "Withdraw";
    return new ResponseEntity<>(actionWord + " ($" + transfer.getAmount() + ") was successful.", HttpStatus.OK);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid, String sessionToken){
    List<Transfer> body;

    if (authService.verifyTransfer(sessionToken, accountid) == false) {
      return ResponseEntity.status(403).body(null);
    }

    try {
      body = transferRepository.findByAccountId(accountid);
    } catch (Exception e) {
      return ResponseEntity.status(500).body(null);
    }
    if(body.isEmpty()) return ResponseEntity.status(404).body(body);
    return ResponseEntity.status(200).body(body);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountId(Long accountid, boolean isDeposit, String sessionToken) {
    if (authService.verifyTransfer(sessionToken, accountid) == false) {
      return ResponseEntity.status(403).body(null);
    }

    List<Transfer> body = transferRepository.findByAccountIdAndIsDeposit(accountid, isDeposit);
    if(body.isEmpty()) return ResponseEntity.status(404).body(body);
    return ResponseEntity.status(200).body(body);
  }

  @Transactional
  public ResponseEntity<String> sendMoneyBetweenTwoAccounts(Long fromAccountId, Long toAccountId, BigDecimal amount, String sessionToken) {
    if (authService.verifyTransfer(sessionToken, fromAccountId) == false) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }

    if (amount == null) {
      return new ResponseEntity<>("You must specify an amount of money to send.", HttpStatus.BAD_REQUEST);
    }
    if (amount.compareTo(new BigDecimal(0)) <= 0) {
      return new ResponseEntity<>("You cannot send that amount of money!", HttpStatus.BAD_REQUEST);
    }

    if(fromAccountId == toAccountId){
      return new ResponseEntity<>("You cannot transfer money from an account to itself!", HttpStatus.BAD_REQUEST);
    }

    // Checking that the accounts specified exist.
    Optional<BankAccount> fromAccountOptional =  bankAccountRepository.findById(fromAccountId);
    if(!fromAccountOptional.isPresent()) {
      return ResponseEntity.status(403).body("The account you are sending monet from doesn't exist!");
    }
    BankAccount fromAccount = fromAccountOptional.get();

    Optional<BankAccount> toAccountOptional =  bankAccountRepository.findById(toAccountId);
    if(!toAccountOptional.isPresent()) {
      return ResponseEntity.status(403).body("The account you are sending monet from doesn't exist!");
    }
    BankAccount toAccount = toAccountOptional.get();

    //Checking that the "from" account has enough money to complete the transfer
    if(fromAccount.getBalance().compareTo(amount) < 0) {
      return ResponseEntity.status(403).body("There isn't enough money in that account to send $" + amount + "!");
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

      //Update the locally stored bankaccount objects to reflect the new balance
      fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
      toAccount.setBalance(toAccount.getBalance().add(amount));

      //Push those changes to the database.
      //FOUR database updates in a row. I hope @Transactional does what I think it does.
      bankAccountRepository.updateBalance(fromAccount.getId(), fromAccount.getBalance());
      bankAccountRepository.updateBalance(toAccount.getId(), toAccount.getBalance());
      transferRepository.save(from);
      transferRepository.save(to);
    return new ResponseEntity<>("Successfully transferred $" + amount + " from account #" + fromAccountId + " to #" + toAccountId, HttpStatus.OK);
  }

  public ResponseEntity<List<Transfer>> getTransfersByAccountAndTime(Long accId, int year, int month, String sessionToken){
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