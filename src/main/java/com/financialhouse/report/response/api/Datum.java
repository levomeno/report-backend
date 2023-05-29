package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Datum {
    public Fx fx;
    public String updated_at;
    public String created_at;
    public Merchant merchant;
    public Transaction transaction;
    public CustomerInfo customerInfo;
    public Acquirer acquirer;
    public boolean refundable;
}
