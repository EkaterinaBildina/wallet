package com.example.wallet.repository;

import com.example.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing entities.
 * <p>
 *     This interface extends {@link JpaRepository}, providing CRUD operations
 *     and additional query methods for entities.
 * </p>
 * @see Wallet
 * @see JpaRepository
 */
@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {

}
