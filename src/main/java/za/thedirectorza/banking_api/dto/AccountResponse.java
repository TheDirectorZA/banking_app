package za.thedirectorza.banking_api.dto;

import java.math.BigDecimal;

public record AccountResponse(Long id, String accountNumber, BigDecimal balance, Long userId) {}
