package com.financialhouse.report;

import com.financialhouse.report.request.LoginRequest;
import com.financialhouse.report.response.api.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LoginControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void postLogin_whenCredentialsValid_receiveOK() {
        LoginRequest loginRequest = new LoginRequest("test@gmail.com", "123456");

        ResponseEntity<LoginResponse> response = testRestTemplate.postForEntity("/api/login", loginRequest, LoginResponse.class);

        assertThat(response.getStatusCode().is2xxSuccessful());
    }
}
