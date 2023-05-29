package com.financialhouse.report;

import com.financialhouse.report.request.LoginRequest;
import com.financialhouse.report.response.api.LoginResponse;
import com.financialhouse.report.service.impl.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoginServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LoginService loginService;

    private LoginResponse loginSuccessResponse;
    private LoginResponse loginFailedResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginSuccessResponse = new LoginResponse("token", "APPROVED", null, null);
        loginFailedResponse = new LoginResponse(null, "DECLINED", 0, "Error: Merchant User credentials is not valid");
    }

    @Test
    public void givenCorrectCredentials_thenReturnSuccessWithToken() {
        String email = "test@example.com";
        String password = "password";
        String url = "/api/v3/merchant/user/login";
        given(restTemplate.postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class)))
                .willReturn(loginSuccessResponse);

        // Act
        LoginResponse actualResponse = loginService.authenticateWithApi(email, password);

        assertNotNull(actualResponse);
        assertNotNull(actualResponse.getToken());
        assertEquals("APPROVED", actualResponse.getStatus());
        verify(restTemplate, Mockito.times(1))
                .postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class));
    }

    @Test
    public void givenFalseCredentials_thenReturn500() {
        String email = "test@example.com";
        String password = "password";
        String url = "/api/v3/merchant/user/login";
        given(restTemplate.postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class)))
                .willReturn(loginFailedResponse);

        // Act
        //LoginResponse actualResponse = loginService.authenticateWithApi(email, password);

        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            LoginResponse actualResponse = loginService.authenticateWithApi(email, password);
//        });
//
//        assertNotNull(actualResponse);
//        assertNotNull(actualResponse.getToken());
//        assertEquals("APPROVED", actualResponse.getStatus());
//        verify(restTemplate, Mockito.times(1))
//                .postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class));
    }

    @Test
    public void testAuthenticateWithApi_Success() {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String url = "https://sandbox-reporting.rpdpymnt.com/api/v3/merchant/user/login";

        LoginRequest request = new LoginRequest(email, password);
        LoginResponse expectedResponse = new LoginResponse();
        expectedResponse.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...");
        expectedResponse.setStatus("APPROVED");

        Mockito.doReturn(expectedResponse)
                .when(restTemplate)
                .postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class));

        // Act
        LoginResponse actualResponse = loginService.authenticateWithApi(email, password);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
        assertEquals("APPROVED", actualResponse.getStatus());
        verify(restTemplate, Mockito.times(1))
                .postForObject(eq(url), eq(request), eq(LoginResponse.class));
    }

    //@Test
    public void testAuthenticateWithApi_Failure()  throws Exception {
        // Arrange
        String email = "test@example.com";
        String password = "password";
        String url = "https://sandbox-reporting.rpdpymnt.com/api/v3/merchant/user/login";

        LoginRequest request = new LoginRequest(email, password);
        String errorMessage = "Error: Merchant User credentials is not valid";
        String errorResponseBody = "{\"code\": 0, \"status\": \"DECLINED\", \"message\": \"" + errorMessage + "\"}";

        when(restTemplate.postForObject(eq(url), any(LoginRequest.class), eq(LoginResponse.class)))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        // Act
        LoginResponse actualResponse = loginService.authenticateWithApi(email, password);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actualResponse.getStatus());
       // assertNull(actualResponse);
        verify(restTemplate, Mockito.times(1))
                .postForObject(eq(url), eq(request), eq(LoginResponse.class));
    }
}
