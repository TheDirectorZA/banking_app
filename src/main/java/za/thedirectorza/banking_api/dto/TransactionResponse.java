package za.thedirectorza.banking_api.dto;

import za.thedirectorza.banking_api.model.Transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        BigDecimal amount,
        LocalDateTime createdAt,
        Long accountId,
        Long relatedAccountId
) {}
