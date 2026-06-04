package za.thedirectorza.banking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.thedirectorza.banking_api.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);
}
