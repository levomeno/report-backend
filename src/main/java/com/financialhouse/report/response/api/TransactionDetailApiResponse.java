package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailApiResponse {

    private Fx fx;
    private Merchant merchant;
    private Transaction transaction;
}
