package com.revature.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Transfer;
import com.revature.service.TransferService;

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> postTransfer(@RequestBody Transfer t, @RequestHeader String sessionToken){
        //Ensuring that time/id values passed in have no effect
        
        Transfer transfer = new Transfer(t.getAccountId(), t.getAmount(), t.getIsDeposit(), t.getDescription());
        return transferService.addNewTransfer(transfer, sessionToken);
    }

    @GetMapping(path = "my")
    public ResponseEntity<List<Transfer>> getTransfersByAccountId(@RequestHeader Long accountid, @RequestHeader String sessionToken){
        return transferService.getTransfersByAccountId(accountid, sessionToken);
    }

    @GetMapping(path = "my/withdraws")
    public ResponseEntity<List<Transfer>> getWithdraws(@RequestHeader Long accountid, @RequestHeader String sessionToken){
        return transferService.getTransfersByAccountId(accountid, false, sessionToken);
    }

    @GetMapping(path = "my/deposits")
    public ResponseEntity<List<Transfer>> getDeposits(@RequestHeader Long accountid, @RequestHeader String sessionToken){
        return transferService.getTransfersByAccountId(accountid, true, sessionToken);
    }

    @GetMapping(path= "my/bytime")
    public ResponseEntity<List<Transfer>> getTransfersByAccountYearMonth(
        @RequestHeader Long accountId,
        @RequestHeader int year,
        @RequestHeader int month,
        @RequestHeader String sessionToken
    ){
        return transferService.getTransfersByAccountAndTime(accountId, year, month, sessionToken);
    }

    @PostMapping(path= "a2a")
    public ResponseEntity<String> sendMoneyBetweenTwoAccounts(
        @RequestHeader Long fromAccount,
        @RequestHeader Long toAccount,
        @RequestHeader BigDecimal amount,
        @RequestHeader String sessionToken
    ){
        return transferService.sendMoneyBetweenTwoAccounts(fromAccount, toAccount, amount, sessionToken);
    }

}
