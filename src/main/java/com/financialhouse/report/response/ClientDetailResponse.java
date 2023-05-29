package com.financialhouse.report.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDetailResponse {

    private String billingFirstName;
    private String billingLastName;
    private String billingAddress1;
    private String email;
    private String billingPhone;
    private String billingCity;
    private String billingCountry;
    private String birthday;
    private String created_at;
}
