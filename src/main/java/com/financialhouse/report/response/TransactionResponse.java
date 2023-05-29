package com.financialhouse.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Integer number;
    private Boolean last;
    private Boolean first;
    private ArrayList<TransactionData> transactions;
}
