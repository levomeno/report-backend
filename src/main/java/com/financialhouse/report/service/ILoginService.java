package com.financialhouse.report.service;

import com.financialhouse.report.response.api.LoginResponse;

public interface ILoginService {
    LoginResponse authenticateWithApi(String email, String password);
}
