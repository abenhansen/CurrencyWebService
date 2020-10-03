package com.example.currencywebservice.controller;

import com.example.currencywebservice.model.Account;
import com.example.currencywebservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Account>> findAccount(@PathVariable Long id) throws ResourceNotFoundException {
        Account account = accountRepository.findByAccountNumber(id);

        EntityModel<Account> resource = EntityModel.of(account); 						// get the resource
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAccounts()); // get link
        resource.add(linkTo.withRel("all-accounts"));										// append the link

        Link selfLink = linkTo(methodOn(this.getClass()).findAccount(id)).withSelfRel(); //add also link to self
        resource.add(selfLink);
        return new ResponseEntity<>(resource, HttpStatus.OK);
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
    public ResponseEntity <Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setAccountNumber(id);
        accountRepository.save(account);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
