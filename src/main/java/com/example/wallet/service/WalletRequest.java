package com.example.wallet.service;

import com.example.wallet.model.OperationType;

import java.util.UUID;

public class WalletRequest {
    private UUID walletId;
    private OperationType operationType;
    private double amount;

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
