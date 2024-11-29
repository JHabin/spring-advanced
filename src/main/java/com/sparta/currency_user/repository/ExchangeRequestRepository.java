package com.sparta.currency_user.repository;

import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.ExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    // 특정 User ID에 대한 환전 요청 조회
    List<ExchangeRequest> findAllByUserId(Long userId);
}
