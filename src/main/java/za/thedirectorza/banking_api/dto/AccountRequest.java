package za.thedirectorza.banking_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AccountRequest(
        @NotNull Long userId,
        @NotNull @Positive BigDecimal initialBalance
) {}
