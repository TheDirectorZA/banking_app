package za.thedirectorza.banking_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.thedirectorza.banking_api.dto.TransactionRequest;
import za.thedirectorza.banking_api.dto.TransactionResponse;
import za.thedirectorza.banking_api.dto.TransferRequest;
import za.thedirectorza.banking_api.exception.InsufficientFundsException;
import za.thedirectorza.banking_api.model.Account;
import za.thedirectorza.banking_api.model.Transaction;
import za.thedirectorza.banking_api.model.Transaction.TransactionType;
import za.thedirectorza.banking_api.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Transactional
    public TransactionResponse deposit(Long accountId, TransactionRequest request) {
        Account account = accountService.getAccount(accountId);
        account.setBalance(account.getBalance().add(request.amount()));
        accountService.save(account);
        return toResponse(record(account, TransactionType.DEPOSIT, request.amount(), null));
    }

    @Transactional
    public TransactionResponse withdraw(Long accountId, TransactionRequest request) {
        Account account = accountService.getAccount(accountId);
        if (account.getBalance().compareTo(request.amount()) < 0)
            throw new InsufficientFundsException("Insufficient funds in account: " + accountId);
        account.setBalance(account.getBalance().subtract(request.amount()));
        accountService.save(account);
        return toResponse(record(account, TransactionType.WITHDRAWAL, request.amount(), null));
    }

    @Transactional
    public List<TransactionResponse> transfer(Long fromAccountId, TransferRequest request) {
        Account from = accountService.getAccount(fromAccountId);
        Account to = accountService.getAccount(request.toAccountId());
        if (from.getBalance().compareTo(request.amount()) < 0)
            throw new InsufficientFundsException("Insufficient funds in account: " + fromAccountId);
        from.setBalance(from.getBalance().subtract(request.amount()));
        to.setBalance(to.getBalance().add(request.amount()));
        accountService.save(from);
        accountService.save(to);
        Transaction out = record(from, TransactionType.TRANSFER_OUT, request.amount(), to.getId());
        Transaction in = record(to, TransactionType.TRANSFER_IN, request.amount(), from.getId());
        return List.of(toResponse(out), toResponse(in));
    }

    public List<TransactionResponse> history(Long accountId) {
        accountService.getAccount(accountId); // validate exists
        return transactionRepository.findByAccountIdOrderByCreatedAtDesc(accountId)
                .stream().map(this::toResponse).toList();
    }

    private Transaction record(Account account, TransactionType type, java.math.BigDecimal amount, Long relatedId) {
        return transactionRepository.save(Transaction.builder()
                .account(account)
                .type(type)
                .amount(amount)
                .createdAt(LocalDateTime.now())
                .relatedAccountId(relatedId)
                .build());
    }

    private TransactionResponse toResponse(Transaction t) {
        return new TransactionResponse(
                t.getId(), t.getType(), t.getAmount(), t.getCreatedAt(),
                t.getAccount().getId(), t.getRelatedAccountId()
        );
    }
}
