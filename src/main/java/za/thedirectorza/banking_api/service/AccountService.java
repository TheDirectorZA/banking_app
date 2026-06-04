package za.thedirectorza.banking_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import za.thedirectorza.banking_api.dto.AccountRequest;
import za.thedirectorza.banking_api.dto.AccountResponse;
import za.thedirectorza.banking_api.exception.ResourceNotFoundException;
import za.thedirectorza.banking_api.model.Account;
import za.thedirectorza.banking_api.model.User;
import za.thedirectorza.banking_api.repository.AccountRepository;
import za.thedirectorza.banking_api.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountResponse create(AccountRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.userId()));
        Account account = accountRepository.save(Account.builder()
                .accountNumber(UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase())
                .balance(request.initialBalance())
                .user(user)
                .build());
        return toResponse(account);
    }

    public List<AccountResponse> findAll() {
        return accountRepository.findAll().stream().map(this::toResponse).toList();
    }

    public AccountResponse findById(Long id) {
        return toResponse(getAccount(id));
    }

    public List<AccountResponse> findByUser(Long userId) {
        return accountRepository.findByUserId(userId).stream().map(this::toResponse).toList();
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found: " + id));
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    private AccountResponse toResponse(Account a) {
        return new AccountResponse(a.getId(), a.getAccountNumber(), a.getBalance(), a.getUser().getId());
    }
}
