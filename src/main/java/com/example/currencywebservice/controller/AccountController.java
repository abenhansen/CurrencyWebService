package com.example.currencywebservice.controller;

import com.example.currencywebservice.model.Account;
import com.example.currencywebservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Account> findAccount(@PathVariable Long id) throws ResourceNotFoundException {
        Account account = accountRepository.findByAccountNumber(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable Long id) {
        Account account = accountRepository.findByAccountNumber(id);
        accountRepository.delete(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account oldAccount = accountRepository.findByAccountNumber(id);
        oldAccount.amount = account.amount;
        oldAccount.currency = account.currency;
        accountRepository.save(oldAccount);
        return new ResponseEntity<>(oldAccount, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountRepository.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}