package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    private BigDecimal beforeExchange;

    private BigDecimal afterExchange;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ExchangeRequest(User user, Currency currency, BigDecimal beforeExchange, BigDecimal afterExchange, String status) {
        this.user = user;
        this.currency = currency;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = status;
    }
    //상태를 업데이트
    public void updateStatus(String updatedStatus){
        this.status = updatedStatus;
    }

}