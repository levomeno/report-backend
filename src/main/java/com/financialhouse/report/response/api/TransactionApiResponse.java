package com.financialhouse.report.response.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionApiResponse {
    public int per_page;
    public int current_page;
    public String next_page_url;
    public String prev_page_url;
    public int from;
    @JsonProperty("to")
    public int myto;
    public ArrayList<Datum> data;
}
