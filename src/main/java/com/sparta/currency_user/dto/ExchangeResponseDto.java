package com.sparta.currency_user.dto;

import com.sparta.currency_user.entity.ExchangeRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExchangeResponseDto {
    private Long id;
    private Long userId;
    private Long currencyId;
    private Double beforeExchange;
    private Double afterExchange;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ExchangeRequest.Status status;

    public ExchangeResponseDto(ExchangeRequest exchangeRequest) {
    this.id = exchangeRequest.getId();
    this.userId = exchangeRequest.getUser().getId();
    this.currencyId = exchangeRequest.getCurrency().getId();
    this.beforeExchange = exchangeRequest.getBeforeExchange();
    this.afterExchange = exchangeRequest.getAfterExchange();
    this.createdAt = exchangeRequest.getCreatedAt();
    this.updatedAt = exchangeRequest.getUpdatedAt();
    this.status = exchangeRequest.getStatus();

    }
}
