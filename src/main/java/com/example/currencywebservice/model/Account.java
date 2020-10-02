package com.example.currencywebservice.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;

    private enum Currency {
        DKK,
        EUR
    }
    @Enumerated(EnumType.STRING)
    private Currency currency;

    private double amount;

    public Account(long accountNumber, Currency currency, double amount) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.amount = amount;
    }

    public Account() {

    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
