package com.financialhouse.report.controller;

import com.financialhouse.report.request.LoginRequest;
import com.financialhouse.report.response.api.LoginResponse;
import com.financialhouse.report.service.ILoginService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    ILoginService loginService;

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Request to login: {}", loginRequest);

        LoginResponse response =  loginService.authenticateWithApi(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok().body(response);
    }
}
