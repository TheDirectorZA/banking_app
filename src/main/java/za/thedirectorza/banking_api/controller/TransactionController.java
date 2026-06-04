package za.thedirectorza.banking_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import za.thedirectorza.banking_api.dto.TransactionRequest;
import za.thedirectorza.banking_api.dto.TransactionResponse;
import za.thedirectorza.banking_api.dto.TransferRequest;
import za.thedirectorza.banking_api.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/accounts/{accountId}/deposit")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse deposit(@PathVariable Long accountId,
                                       @Valid @RequestBody TransactionRequest request) {
        return transactionService.deposit(accountId, request);
    }

    @PostMapping("/accounts/{accountId}/withdraw")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse withdraw(@PathVariable Long accountId,
                                        @Valid @RequestBody TransactionRequest request) {
        return transactionService.withdraw(accountId, request);
    }

    @PostMapping("/accounts/{accountId}/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TransactionResponse> transfer(@PathVariable Long accountId,
                                              @Valid @RequestBody TransferRequest request) {
        return transactionService.transfer(accountId, request);
    }

    @GetMapping("/accounts/{accountId}")
    public List<TransactionResponse> history(@PathVariable Long accountId) {
        return transactionService.history(accountId);
    }
}
