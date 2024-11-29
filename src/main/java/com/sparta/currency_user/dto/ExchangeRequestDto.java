package com.sparta.currency_user.dto;

import lombok.Getter;
import com.sparta.currency_user.entity.ExchangeRequest;
@Getter
@Setter
public class ExchangeRequestDto {
    private Long id;
    private Long currencyId;
    private Double beforeExchange;
    private Double afterExchange;

    public ExchangeRequestDto(Long id, Double beforeExchange,Double afterExchange) {
        this.id = id;
        this.currencyId = currencyId;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
    }
}
