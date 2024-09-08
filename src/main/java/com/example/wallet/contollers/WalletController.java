package com.example.wallet.contollers;


import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Controller for working with user wallets.
 * Supports API-methods: POST (running operation in wallet)
 * and GET (returns info about wallet by id).
 *
 */
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    /**
     * Running the operation in wallet.
     * @param request data to perform the transaction, including wallet ID, transaction type and amount.
     * @return empty answer with 200 (OK) status codes in case of successful completion of the operation.
     */
    @PostMapping
    public ResponseEntity<Void> performOperation(@RequestBody WalletOperationRequest request) {
        walletService.performOperation(request.getWalletId(), request.getOperationType(), request.getAmount());
        return ResponseEntity.ok().build();
    }


    /**
     * Receives info about wallet by ID.
     * @param walletId
     * @return object Wallet with wallet details, if found that, else 404 (NOT FOUND) status code.
     */
    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWallet(@PathVariable UUID walletId) {
        return walletService.getWallet(walletId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Static class for request to perform an operation with a wallet.
     * Have info about wallet ID, Operation Type (DEPOSIT, WITHDRAW), amont.
     * Realize getters, setters for each one parameter.
     */
    public static class WalletOperationRequest {
        private UUID walletId;
        private String operationType;
        private double amount;


        // Getters and Setters
        public UUID getWalletId() {
            return walletId;
        }

        public void setWalletId(UUID walletId) {
            this.walletId = walletId;
        }

        public String getOperationType() {
            return operationType;
        }

        public void setOperationType(String operationType) {
            this.operationType = operationType;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public WalletOperationRequest(UUID walletId, String operationType, double amount) {
            this.walletId = walletId;
            this.operationType = operationType;
            this.amount = amount;
        }
    }
}
