package com.financialhouse.report.response.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agent{
    public int id;
    public String customerIp;
    public String customerUserAgent;
    public String merchantIp;
    public String merchantUserAgent;
    public String created_at;
    public String updated_at;
    public String deleted_at;
}
