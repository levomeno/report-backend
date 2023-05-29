package com.financialhouse.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {

    private String createdAt;
    private String merchantName;
    private String acquirerName;
    private Integer originalAmount;
    private String originalCurrency;
    private String status;
    private String transactionId;
}
