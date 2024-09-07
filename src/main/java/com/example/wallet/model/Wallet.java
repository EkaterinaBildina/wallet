package com.example.wallet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;


@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    private UUID walletId;
    private double balance;

    public Wallet() {
        this.balance = 0;
    }

    /**
     * Getters and Setters
     * @return walletId, balance
     */
    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     *
     * @param amount
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     *
     * @param amount
     */
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds in the account");
        }
    }
}
