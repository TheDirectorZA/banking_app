package za.thedirectorza.banking_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.thedirectorza.banking_api.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(Long userId);
}
