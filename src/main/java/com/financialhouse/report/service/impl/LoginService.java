package com.financialhouse.report.service.impl;

import com.financialhouse.report.request.LoginRequest;
import com.financialhouse.report.response.api.LoginResponse;
import com.financialhouse.report.service.ILoginService;
import com.financialhouse.report.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService implements ILoginService {

    @Value("${ROOT_URL}")
    private String url;

    private static String URL_STATIC;

    @Value("${ROOT_URL}")
    public void setNameStatic(String url){
        LoginService.URL_STATIC = url;
    }
    @Autowired
    RestTemplate restTemplate;

    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public LoginResponse authenticateWithApi(String email, String password) {
        String url = URL_STATIC + Constants.LOGIN_URL;

        LoginRequest request = new LoginRequest(email, password);

        LoginResponse createdUser = restTemplate.postForObject(url, request, LoginResponse.class);
        return createdUser;
    }
}
