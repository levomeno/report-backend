package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Acquirer {
    public int id;
    public String name;
    public String code;
    public String type;
}
