package com.revature.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.revature.model.Transfer;
import com.revature.service.TransferService;

@RestController
@RequestMapping(path = "api/v1/transfer")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    //TODO: Delete this function, it should only be implemented for testing
    @GetMapping(path = "all")
	public List<Transfer> getTransfers(){
		return transferService.getTransfers();
	}

    @PostMapping("new")
    public ResponseEntity<String> postTransfer(@RequestBody Transfer transfer){
        return transferService.addNewTransfer(transfer);
    }

    @GetMapping(path = "my")
    public List<Transfer> getTransfersByAccountId(@RequestBody Long accountid){
        return transferService.getTransfersByAccountId(accountid);
    }

    @GetMapping(path = "my/withdraws")
    public List<Transfer> getWithdraws(@RequestBody Long accountid){
        return transferService.getTransfersByAccountId(accountid, false);
    }

    @GetMapping(path = "my/deposits")
    public List<Transfer> getDeposits(@RequestBody Long accountid){
        return transferService.getTransfersByAccountId(accountid, true);
    }


    @PostMapping(path= "a2a")
    public ResponseEntity<String> sendMoneyBetweenTwoAccounts(
        @RequestBody ObjectNode json
    ){
        if(
            json.get("fromAccount") == null ||
            json.get("toAccount") == null ||
            json.get("amount") == null
        ) {
            return new ResponseEntity<>("You are missing data from your request!", HttpStatus.BAD_REQUEST);
        }

        try {
            BigDecimal amount = new BigDecimal(json.get("amount").asText());
            return transferService.sendMoneyBetweenTwoAccounts(
                json.get("fromAccount").asLong(), 
                json.get("toAccount").asLong(), 
                amount
            );
        } catch (Exception e) {
            return new ResponseEntity<>("Bad data in request!", HttpStatus.BAD_REQUEST);
        }
    }
}
