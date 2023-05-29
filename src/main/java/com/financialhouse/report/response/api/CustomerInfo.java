package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfo {

    private String billingFirstName;
    private String billingLastName;
    private String email;
    private String number;
    private String billingTitle;
    private String billingAddress1;
    private String billingAddress2;
    private String billingPhone;
    private String billingCity;
    private String billingPostCode;
    private String billingCountry;
    private String billingState;
    private String birthday;
    private String updated_at;
    private String created_at;
    private int id;
}
