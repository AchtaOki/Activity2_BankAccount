package com.sid.bankaccountservice.mappers;

import com.sid.bankaccountservice.dto.BankAccountResponseDTO;
import com.sid.bankaccountservice.entities.BankAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMappar {
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount,bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
