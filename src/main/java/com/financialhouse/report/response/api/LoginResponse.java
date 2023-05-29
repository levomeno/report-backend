package com.financialhouse.report.response.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class LoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;
}
