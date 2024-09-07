package com.example.wallet.service;

import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Service Class for managing user wallets.
 * The class provides methods for performing wallet related operations:
 * creating a wallet, balance management, transaction processing,
 * getting info about the wallet status.
 */
@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;


    /**
     * Performing operations with an electronic wallet
     * FindWallet by WalletId. New Wallet creation in case od not found error.
     * Setting the identificator of wallet - necessary to save the identifiers
     * correctly, even if a new wallet was created.
     * @param walletId
     * Processing of an operation type (DEPOSIT, WITHDRAW): inrease or decrease of balance.
     * @param operationType
     * @param amount
     * Save changing after the operation is completed.
     */
    public void performOperation(UUID walletId, String operationType, double amount) {
        Wallet wallet = walletRepository.findById(walletId).orElse(new Wallet());
        wallet.setWalletId(walletId);
        if ("DEPOSIT".equalsIgnoreCase(operationType)) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else if ("WITHDRAW".equalsIgnoreCase(operationType) && wallet.getBalance() >= amount) {
            wallet.setBalance(wallet.getBalance() - amount);
        }
        walletRepository.save(wallet);
    }


    /**
     * Search for Wallet by walletId.
     * @param walletId
     * @return Optionl with wallet, otherwise empty wallet
     */
    public Optional<Wallet> getWallet(UUID walletId){
        return walletRepository.findById(walletId);
    }


}


