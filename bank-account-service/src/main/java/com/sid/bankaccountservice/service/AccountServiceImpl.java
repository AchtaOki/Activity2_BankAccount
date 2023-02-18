package com.sid.bankaccountservice.service;

import com.sid.bankaccountservice.dto.BankAccountRequestDTO;
import com.sid.bankaccountservice.dto.BankAccountResponseDTO;
import com.sid.bankaccountservice.entities.BankAccount;
import com.sid.bankaccountservice.mappers.AccountMappar;
import com.sid.bankaccountservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMappar accountMappar;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount= BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount saveBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO =  accountMappar.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
