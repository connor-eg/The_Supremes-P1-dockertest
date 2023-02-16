package com.revature.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Transfer;
import com.revature.repository.TransferRepository;

@Service
public class TransferService {

	private final TransferRepository transferRepository;

	@Autowired
	public TransferService(TransferRepository transferRepository) {
		this.transferRepository = transferRepository;
	}

    public List<Transfer> getTransfers() {
        return transferRepository.findAll();
    }

    public void addNewTransfer(Transfer transfer) {
		transferRepository.save(transfer);
    }
}
