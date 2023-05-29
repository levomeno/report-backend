package com.financialhouse.report.service;

import com.financialhouse.report.request.DetailRequest;
import com.financialhouse.report.request.TransactionRequest;
import com.financialhouse.report.response.ClientDetailResponse;
import com.financialhouse.report.response.TransactionDetailResponse;
import com.financialhouse.report.response.TransactionResponse;

public interface IReportService {

    TransactionResponse getTransactionList(TransactionRequest request, String apiKey, int page);
    TransactionDetailResponse getTransactionDetail(DetailRequest request, String apiKey);
    ClientDetailResponse getClientDetail(DetailRequest request, String apiKey);
}
