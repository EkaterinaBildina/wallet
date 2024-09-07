package com.example.wallet.contollers;


import com.example.wallet.model.OperationType;
import com.example.wallet.model.Wallet;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.service.WalletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {
    private final WalletRepository walletRepository;


    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @PostMapping
    public ResponseEntity<Wallet> createOrUpdateWallet(@RequestBody @Validated WalletRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId()).orElse(new Wallet());
        if (request.getOperationType() == OperationType.DEPOSIT) {
            wallet.deposit(request.getAmount());
        } else if (request.getOperationType() == OperationType.WITHDRAW) {
            wallet.withdraw(request.getAmount());
        }
        walletRepository.save(wallet);
        return ResponseEntity.ok(wallet);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return ResponseEntity.ok(wallet);
    }

}
