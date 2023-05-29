package com.financialhouse.report.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

   // @Pattern(regexp = DATE_PATTERN, message = "From Date should be in the format 'yyyy-MM-dd'")
    private String fromDate;

   // @Pattern(regexp = DATE_PATTERN, message = "To Date should be in the format 'yyyy-MM-dd'")
    private String toDate;
}
