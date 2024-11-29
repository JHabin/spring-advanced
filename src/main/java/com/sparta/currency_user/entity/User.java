package com.sparta.currency_user.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExchangeRequest> exchangeRequests = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {}

    // 편의 메서드: 환전 요청 추가
    public void addExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequests.add(exchangeRequest);
        exchangeRequest.setUser(this);
    }

    // 편의 메서드: 환전 요청 제거
    public void removeExchangeRequest(ExchangeRequest exchangeRequest) {
        exchangeRequests.remove(exchangeRequest);
        exchangeRequest.setUser(null);
    }
}