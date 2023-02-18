package com.sid.bankaccountservice.service;

import com.sid.bankaccountservice.dto.BankAccountRequestDTO;
import com.sid.bankaccountservice.dto.BankAccountResponseDTO;
import com.sid.bankaccountservice.entities.BankAccount;
import com.sid.bankaccountservice.enums.AccountType;

public interface AccountService {
  BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO);
}
