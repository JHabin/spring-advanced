package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long currencyId;
    private BigDecimal beforeExchange;
    private BigDecimal afterExchange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;

    public ExchangeResponseDto(ExchangeRequest exchangeRequest) {
    this.id = exchangeRequest.getId();
    this.userId = exchangeRequest.getUser().getId();
    this.currencyId = exchangeRequest.getCurrency().getId();
    this.beforeExchange = exchangeRequest.getBeforeExchange();
    this.afterExchange = exchangeRequest.getAfterExchange();
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
    this.status = exchangeRequest.getStatus();

    }
    public ExchangeResponseDto(Long userId, Long currencyId, BigDecimal beforeExchange,
                               BigDecimal afterExchange, String status, LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public static ExchangeResponseDto toDto(ExchangeRequest exchangeRequest) {
        return new ExchangeResponseDto(
                exchangeRequest.getUser().getId(),
                exchangeRequest.getCurrency().getId(),
                exchangeRequest.getBeforeExchange(),
                exchangeRequest.getAfterExchange(),
                exchangeRequest.getStatus(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
