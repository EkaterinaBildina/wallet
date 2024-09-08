package com.example.wallet;



import com.example.wallet.contollers.WalletController;
import com.example.wallet.model.Wallet;
import com.example.wallet.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@ExtendWith(MockitoExtension.class)
public class WalletControllerTest {

    @InjectMocks
    private WalletController walletController;

    @Mock
    private WalletService walletService;

    private WalletController.WalletOperationRequest validRequest;
    private UUID walletId;
    private Wallet wallet;

    @BeforeEach
    void setUp() {
        walletId = UUID.randomUUID();
        validRequest = new WalletController.WalletOperationRequest(walletId, "DEPOSIT", 100);
        wallet = new Wallet(walletId, 1000);
    }

    @Test
    @Rollback
    void testPerformOperation() {
        // Arrange
        doNothing().when(walletService).performOperation(any(UUID.class), any(), any(Double.class));
        // Act
        ResponseEntity<Void> response = walletController.performOperation(validRequest);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testGetWalletFound(){
        // Arrange
        when(walletService.getWallet(eq(walletId))).thenReturn(Optional.of(wallet));
        // Act
        ResponseEntity<Wallet> response = walletController.getWallet(walletId);
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(wallet,response.getBody());
    }

    @Test
    void testGetWalletNotFound(){
        // Arrange
        when(walletService.getWallet(walletId)).thenReturn(Optional.empty());
        // Act
        ResponseEntity<Wallet> response = walletController.getWallet(walletId);
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}

