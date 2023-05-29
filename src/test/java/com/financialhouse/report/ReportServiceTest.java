package com.financialhouse.report;

import com.financialhouse.report.request.DetailRequest;
import com.financialhouse.report.request.TransactionRequest;
import com.financialhouse.report.response.ClientDetailResponse;
import com.financialhouse.report.response.TransactionData;
import com.financialhouse.report.response.TransactionDetailResponse;
import com.financialhouse.report.response.TransactionResponse;
import com.financialhouse.report.response.api.*;
import com.financialhouse.report.service.IReportService;
import com.financialhouse.report.service.impl.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class ReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private IReportService reportService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        reportService = new ReportService(restTemplate);
    }

  //  @Test
//    public void testGetTransactionList() {
//        // Mocked response data
//        TransactionApiResponse mockedApiResponse = new TransactionApiResponse();
//        mockedApiResponse.setData(Collections.singletonList(createMockedTransactionData()));
//
//        // Mocked request and API key
//        TransactionRequest request = new TransactionRequest();
//        String apiKey = "testApiKey";
//        int page = 1;
//
//        // Mocking restTemplate behavior
//        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(TransactionApiResponse.class)))
//                .thenReturn(mockedApiResponse);
//
//        // Performing the actual method call
//        TransactionResponse response = reportService.getTransactionList(request, apiKey, page);
//
//        // Assertions
//        assertEquals(1, response.getTransactions().size());
//        assertEquals(1, response.getNumber());
//        assertEquals(true, response.getLast());
//        assertEquals(false, response.getFirst());
//    }

    @Test
    public void testGetTransactionDetail() {
        // Mocked response data
        TransactionDetailApiResponse mockedApiResponse = createMockedTransactionDetailApiResponse();

        // Mocked request and API key
        DetailRequest request = new DetailRequest();
        String apiKey = "testApiKey";

        // Mocking restTemplate behavior
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(TransactionDetailApiResponse.class)))
                .thenReturn(mockedApiResponse);

        // Performing the actual method call
        TransactionDetailResponse response = reportService.getTransactionDetail(request, apiKey);

        // Assertions
        assertEquals("testTransactionId", response.getTransactionId());
        assertEquals("testChannel", response.getChannel());
        assertEquals("testMessage", response.getMessage());
        assertEquals("testOperation", response.getOperation());
        assertEquals("testStatus", response.getStatus());
        assertEquals("testType", response.getType());
        assertEquals("testReferenceNo", response.getReferenceNo());
        assertEquals("testCreatedAt", response.getCreatedAt());
       // assertEquals("testMerchantName", response.getMerchantName());
        assertEquals(1000, response.getOriginalAmount());
        assertEquals("testOriginalCurrency", response.getOriginalCurrency());
    }

    @Test
    public void testGetClientDetail() {
        // Mocked response data
        ClientApiResponse mockedApiResponse = createMockedClientApiResponse();

        // Mocked request and API key
        DetailRequest request = new DetailRequest();
        String apiKey = "testApiKey";

        // Mocking restTemplate behavior
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(ClientApiResponse.class)))
                .thenReturn(mockedApiResponse);

        // Performing the actual method call
        ClientDetailResponse response = reportService.getClientDetail(request, apiKey);

        // Assertions
        assertEquals("testBillingFirstName", response.getBillingFirstName());
        assertEquals("testBillingLastName", response.getBillingLastName());
        assertEquals("testBillingAddress1", response.getBillingAddress1());
        assertEquals("testEmail", response.getEmail());
        assertEquals("testBillingPhone", response.getBillingPhone());
        assertEquals("testBillingCity", response.getBillingCity());
        assertEquals("testBillingCountry", response.getBillingCountry());
        assertEquals("testBirthday", response.getBirthday());
        assertEquals("testCreatedAt", response.getCreated_at());
    }

    // Helper method to create a mocked TransactionData object
    private TransactionData createMockedTransactionData() {
        TransactionData transactionData = new TransactionData();
        transactionData.setCreatedAt("testCreatedAt");
        transactionData.setMerchantName("testMerchantName");
        transactionData.setAcquirerName("testAcquirerName");
        transactionData.setOriginalAmount(1000);
        transactionData.setOriginalCurrency("testOriginalCurrency");
        transactionData.setStatus("testStatus");
        transactionData.setTransactionId("testTransactionId");
        return transactionData;
    }

    // Helper method to create a mocked TransactionDetailApiResponse object
    private TransactionDetailApiResponse createMockedTransactionDetailApiResponse() {
        TransactionDetailApiResponse response = new TransactionDetailApiResponse();
        Transaction transactionDetail = new Transaction();
        Merchant merchant = new Merchant();
        transactionDetail.setMerchant(merchant);
        transactionDetail.getMerchant().setTransactionId("testTransactionId");
        transactionDetail.getMerchant().setChannel("testChannel");
        transactionDetail.getMerchant().setMessage("testMessage");
        transactionDetail.getMerchant().setOperation("testOperation");
        transactionDetail.getMerchant().setStatus("testStatus");
        transactionDetail.getMerchant().setType("testType");
        transactionDetail.getMerchant().setReferenceNo("testReferenceNo");
        transactionDetail.getMerchant().setCreated_at("testCreatedAt");
        transactionDetail.getMerchant().setName("testMerchantName");
        response.setTransaction(transactionDetail);
        response.setMerchant(new Merchant());
        response.setFx(new Fx());
        response.getFx().setMerchant(new Merchant());
        response.getFx().getMerchant().setOriginalAmount(1000);
        response.getFx().getMerchant().setOriginalCurrency("testOriginalCurrency");
        return response;
    }

    // Helper method to create a mocked ClientApiResponse object
    private ClientApiResponse createMockedClientApiResponse() {
        ClientApiResponse response = new ClientApiResponse();
        response.setCustomerInfo(new CustomerInfo());
        response.getCustomerInfo().setBillingFirstName("testBillingFirstName");
        response.getCustomerInfo().setBillingLastName("testBillingLastName");
        response.getCustomerInfo().setBillingAddress1("testBillingAddress1");
        response.getCustomerInfo().setEmail("testEmail");
        response.getCustomerInfo().setBillingPhone("testBillingPhone");
        response.getCustomerInfo().setBillingCity("testBillingCity");
        response.getCustomerInfo().setBillingCountry("testBillingCountry");
        response.getCustomerInfo().setBirthday("testBirthday");
        response.getCustomerInfo().setCreated_at("testCreatedAt");
        return response;
    }
}

