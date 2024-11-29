package com.sparta.currency_user.service;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.Currency;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.entity.User;
import com.sparta.currency_user.repository.CurrencyRepository;
import com.sparta.currency_user.repository.ExchangeRequestRepository;
import com.sparta.currency_user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExchangeRequestService {

    private final ExchangeRequestRepository exchangeRequestRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public ExchangeRequestService(ExchangeRequestRepository exchangeRequestRepository,
                                  UserRepository userRepository,
                                  CurrencyRepository currencyRepository) {
        this.exchangeRequestRepository = exchangeRequestRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }
    @Transactional
    public ExchangeResponseDto createExchangeRequest(ExchangeRequestDto exchangeRequestDto) {
        // 사용자 조회
        User user = userRepository.findById(exchangeRequestDto.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.")
        );

        // 환전 대상 통화 조회
        Currency currency = currencyRepository.findById(exchangeRequestDto.getCurrencyId()).orElseThrow(() ->
                new IllegalArgumentException("해당 통화를 찾을 수 없습니다.")
        );

        // 환전 금액 계산
        BigDecimal beforeExchange = BigDecimal.valueOf(exchangeRequestDto.getBeforeExchange().doubleValue());
        BigDecimal exchangeRate = currency.getExchangeRate();

        // divide 연산
        BigDecimal afterExchange = beforeExchange.divide(exchangeRate, 2, RoundingMode.HALF_UP);

        // 환전 요청 생성
        ExchangeRequest exchangeRequest = new ExchangeRequest(
                user,
                currency,
                exchangeRequestDto.getBeforeExchange(),
                afterExchange,
                exchangeRequestDto.getStatus()
        );
        // 데이터 저장
        exchangeRequestRepository.save(exchangeRequest);

        // 응답 생성
        return new ExchangeResponseDto(exchangeRequest);
    }
        // 특정 고객의 환전 요청 조회
        public List<ExchangeResponseDto> getExchangeRequestsByUserId(Long userId) {
            // 사용자 조회
            userRepository.findById(userId).orElseThrow(() ->
                    new IllegalArgumentException("해당 사용자를 찾을 수 없습니다.")
            );
            List<ExchangeRequest> exchangeRequests = exchangeRequestRepository.findAllByUserId(userId);
            List<ExchangeResponseDto> responses = new ArrayList<>();

            for (ExchangeRequest exchangeRequest : exchangeRequests) {
                responses.add(new ExchangeResponseDto(exchangeRequest));
            }

            return responses;
        }
    // 특정 환전 요청 상태를 'cancelled'로 변경
    @Transactional
    public ExchangeResponseDto cancelExchangeRequest(Long id, String status) {
        // 환전 요청 조회
        ExchangeRequest exchangeRequest = exchangeRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 환전 요청을 찾을 수 없습니다."));

        // 상태 변경
        exchangeRequest.updateStatus(status);
        exchangeRequest.setUpdatedAt(LocalDateTime.now());

        // 변경된 객체 저장
        return ExchangeResponseDto.toDto(exchangeRequest);
    }


}
