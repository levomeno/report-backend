package com.financialhouse.report.controller;

import com.financialhouse.report.request.DetailRequest;
import com.financialhouse.report.request.TransactionRequest;
import com.financialhouse.report.response.ClientDetailResponse;
import com.financialhouse.report.response.TransactionDetailResponse;
import com.financialhouse.report.response.TransactionResponse;
import com.financialhouse.report.service.IReportService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    IReportService reportService;

    @PostMapping("/transaction/list")
    ResponseEntity<TransactionResponse> getTransactionList(@RequestHeader("API_KEY") String apiKey,
                                                           @RequestBody @Valid TransactionRequest request,
                                                           @RequestParam(value = "page", required = false) Integer page) {
        logger.info("Request to transaction list: {}", request);
        logger.info("Api Key: {}", apiKey);

        TransactionResponse response = reportService.getTransactionList(request, apiKey, page == null ? 0: page);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/transaction")
    ResponseEntity<TransactionDetailResponse> getTransactionDetail(@RequestHeader("API_KEY") String apiKey,
                                                                   @RequestBody DetailRequest request) {
        logger.info("Request to transaction detail : {}", request);
        logger.info("Api Key: {}", apiKey);

        TransactionDetailResponse response = reportService.getTransactionDetail(request, apiKey);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/client")
    ResponseEntity<ClientDetailResponse> getClientDetail(@RequestHeader("API_KEY") String apiKey,
                                                         @RequestBody DetailRequest request) {
        logger.info("Request to client detail : {}", request);
        logger.info("Api Key: {}", apiKey);

        ClientDetailResponse response = reportService.getClientDetail(request, apiKey);

        return ResponseEntity.ok(response);
    }
}
