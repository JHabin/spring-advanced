package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    private Double beforeExchange;

    private Double afterExchange;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ExchangeRequest(User user, Currency currency, Double beforeExchange, Double afterExchange) {
        this.user = user;
        this.currency = currency;
        this.beforeExchange = beforeExchange;
        this.afterExchange = afterExchange;
        this.status = Status.NORMAL;
    }
    //상태를 업데이트
    public void updateStatus(Status updatedStatus){
        this.status = updatedStatus;
    }
    public enum Status {
       NORMAL, CANCELLED
    }
}