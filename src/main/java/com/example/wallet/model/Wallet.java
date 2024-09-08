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

    public Wallet(UUID walletId, double balance) {
        this.walletId = walletId;
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
}
