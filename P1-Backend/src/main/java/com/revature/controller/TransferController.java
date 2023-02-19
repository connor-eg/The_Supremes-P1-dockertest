package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Transfer;
import com.revature.service.TransferService;

@RestController
@RequestMapping(path = "api/v1/transfer")
public class TransferController {
    private final TransferService transferService;

    @Autowired
    public TransferController(TransferService transferService){
        this.transferService = transferService;
    }

    @GetMapping
	public List<Transfer> getTransfers(){
		return transferService.getTransfers();
	}

    @PostMapping
    public void addNewTransfer(Transfer transfer){
        transferService.addNewTransfer(transfer);
    }
}
