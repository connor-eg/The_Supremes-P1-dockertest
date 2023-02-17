package com.revature.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Transfer;
import com.revature.service.TransferService;

@RestController
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    //TODO: Delete this function, it should only be implemented for testing
    @GetMapping(path = "api/v1/transfer")
	public List<Transfer> getTransfers(){
		return transferService.getTransfers();
	}

    @PostMapping(path = "api/v1/transfer")
    public void postTransfer(@RequestBody Transfer transfer){
        transferService.addNewTransfer(transfer);
    }

    @GetMapping(path = "api/v1/transfer/perAccount")
    public List<Transfer> getTransfersByAccountId(@RequestBody Long accountid){
        return transferService.getTransfersByAccountId(accountid);
    }
}
