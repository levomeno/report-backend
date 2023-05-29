package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {

    private int originalAmount;
    private String originalCurrency;
    private int convertedAmount;
    private String convertedCurrency;
    private int id;
    private String name;
    private boolean allowPartialRefund;
    private boolean allowPartialCapture;
    private String referenceNo;
    private String status;
    private String customData;
    private String type;
    private String operation;
    private String created_at;
    private String message;
    private String transactionId;
    private String channel;
    private Agent agent;
}
