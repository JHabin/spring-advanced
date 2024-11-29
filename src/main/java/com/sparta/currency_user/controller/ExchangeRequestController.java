package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.CurrencyRequestDto;
import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.entity.ExchangeRequest;
import com.sparta.currency_user.service.ExchangeRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/exchange")
public class ExchangeRequestController {

    private final ExchangeRequestService exchangeRequestService;

    public ExchangeRequestController(ExchangeRequestService exchangeRequestService) {
        this.exchangeRequestService = exchangeRequestService;
    }
    // CREATE
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchangeRequest(
            @Valid @RequestBody ExchangeRequestDto exchangeRequestDto) {
        return ResponseEntity.ok().body(exchangeRequestService.createExchangeRequest(exchangeRequestDto));

    }

    // READ
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> getExchangeRequestsByUserId(@PathVariable Long userId) {
        List<ExchangeResponseDto> exchangeRequests = exchangeRequestService.getExchangeRequestsByUserId(userId);
        return ResponseEntity.ok(exchangeRequests);
    }
    // UPDATE
    @PutMapping("/{exchangeRequestId}/")
    public ResponseEntity<ExchangeResponseDto> cancelExchangeRequest(@PathVariable Long exchangeRequestId,
                                                                     @RequestParam String status) {
        ExchangeResponseDto cancelledRequest = exchangeRequestService.cancelExchangeRequest(exchangeRequestId, status);
        return ResponseEntity.ok(cancelledRequest);
    }
}
