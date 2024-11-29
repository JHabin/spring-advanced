package com.sparta.currency_user.dto;

import lombok.Getter;
import com.sparta.currency_user.entity.ExchangeRequest;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRequestDto {
    private Long userId;
    private Long currencyId;
    private BigDecimal beforeExchange;
    private BigDecimal afterExchange;
    private String status;

    public ExchangeRequestDto(Long userId, BigDecimal beforeExchange,BigDecimal afterExchange, String status) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = status;
    }
}
