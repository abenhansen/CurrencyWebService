package com.example.currencywebservice.repository;

import com.example.currencywebservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByAccountNumber(long accountNumber);

}
