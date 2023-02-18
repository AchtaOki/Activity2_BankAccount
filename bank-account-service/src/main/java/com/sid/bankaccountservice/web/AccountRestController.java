package com.sid.bankaccountservice.web;

import com.sid.bankaccountservice.dto.BankAccountRequestDTO;
import com.sid.bankaccountservice.dto.BankAccountResponseDTO;
import com.sid.bankaccountservice.entities.BankAccount;
import com.sid.bankaccountservice.mappers.AccountMappar;
import com.sid.bankaccountservice.repositories.BankAccountRepository;
import com.sid.bankaccountservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMappar accountMappar;

    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMappar accountMappar){
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMappar = accountMappar;
    }
   @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Account %s not found",id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){

        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAccountRepository.findById(id).orElseThrow();
        if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAccountRepository.deleteById(id);

    }
}
