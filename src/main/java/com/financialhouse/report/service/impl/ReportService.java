package com.financialhouse.report.service.impl;

import com.financialhouse.report.request.DetailRequest;
import com.financialhouse.report.request.TransactionRequest;
import com.financialhouse.report.response.ClientDetailResponse;
import com.financialhouse.report.response.TransactionData;
import com.financialhouse.report.response.TransactionDetailResponse;
import com.financialhouse.report.response.TransactionResponse;
import com.financialhouse.report.response.api.Acquirer;
import com.financialhouse.report.response.api.ClientApiResponse;
import com.financialhouse.report.response.api.TransactionApiResponse;
import com.financialhouse.report.response.api.TransactionDetailApiResponse;
import com.financialhouse.report.service.IReportService;
import com.financialhouse.report.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService implements IReportService {

    @Value("${ROOT_URL}")
    private String url;

    private static String URL_STATIC;

    @Value("${ROOT_URL}")
    public void setNameStatic(String url){
        ReportService.URL_STATIC = url;
    }

    @Autowired
    RestTemplate restTemplate;

    public ReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TransactionResponse getTransactionList(TransactionRequest request, String apiKey, int page) {
        String url = URL_STATIC + Constants.TRANSACTION_LIST_URL;
        if (page > 0) {
             url = url + "?page=" + Integer.toString(page);
        }

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", apiKey);

        // build the request
        HttpEntity<TransactionRequest> requestBody = new HttpEntity<>(request, headers);

        // send POST request
       TransactionApiResponse response = restTemplate.postForObject(url, requestBody, TransactionApiResponse.class);

        List<TransactionData> dataList = response.getData().stream()
                .map(d ->
                        new TransactionData(
                                d.getCreated_at(),
                                d.merchant.getName(),
                                Optional.ofNullable(d.getAcquirer()).map(Acquirer::getName).orElse(null),
                                d.fx.merchant.getOriginalAmount(),
                                d.fx.merchant.getOriginalCurrency(),
                                d.transaction.merchant.getStatus(),
                                d.transaction.merchant.getTransactionId()))
                .collect(Collectors.toList());

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTransactions((ArrayList<TransactionData>) dataList);
        transactionResponse.setNumber(response.getCurrent_page());
        transactionResponse.setLast(response.getNext_page_url() == null);
        transactionResponse.setFirst(response.getPrev_page_url() == null);

        return transactionResponse;
    }

    @Override
    public TransactionDetailResponse getTransactionDetail(DetailRequest request, String apiKey) {
        String url = URL_STATIC + Constants.TRANSACTION_URL;

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", apiKey);

        // build the request
        HttpEntity<DetailRequest> requestBody = new HttpEntity<>(request, headers);

        // send POST request
        TransactionDetailApiResponse response = restTemplate.postForObject(url, requestBody, TransactionDetailApiResponse.class);

        TransactionDetailResponse transactionDetailResponse = new TransactionDetailResponse();
        transactionDetailResponse.setTransactionId(response.getTransaction().getMerchant().getTransactionId());
        transactionDetailResponse.setChannel(response.getTransaction().getMerchant().getChannel());
        transactionDetailResponse.setMessage(response.getTransaction().getMerchant().getMessage());
        transactionDetailResponse.setOperation(response.getTransaction().getMerchant().getOperation());
        transactionDetailResponse.setStatus(response.getTransaction().getMerchant().getStatus());
        transactionDetailResponse.setType(response.getTransaction().getMerchant().getType());
        transactionDetailResponse.setReferenceNo(response.getTransaction().getMerchant().getReferenceNo());
        transactionDetailResponse.setCreatedAt(response.getTransaction().getMerchant().getCreated_at());
        transactionDetailResponse.setMerchantName(response.getMerchant().getName());
        transactionDetailResponse.setOriginalAmount(response.getFx().getMerchant().getOriginalAmount());
        transactionDetailResponse.setOriginalCurrency(response.getFx().getMerchant().getOriginalCurrency());
        transactionDetailResponse.setTransactionId(response.getTransaction().getMerchant().getTransactionId());

        return transactionDetailResponse;
    }

    @Override
    public ClientDetailResponse getClientDetail(DetailRequest request, String apiKey) {
        String url = URL_STATIC + Constants.CLIENT_URL;

        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", apiKey);

        // build the request
        HttpEntity<DetailRequest> requestBody = new HttpEntity<>(request, headers);

        // send POST request
        ClientApiResponse response = restTemplate.postForObject(url, requestBody, ClientApiResponse.class);

        ClientDetailResponse clientDetailResponse = new ClientDetailResponse();
        if (response.getCustomerInfo() != null) {
            clientDetailResponse.setBillingFirstName(response.getCustomerInfo().getBillingFirstName());
            clientDetailResponse.setBillingLastName(response.getCustomerInfo().getBillingLastName());
            clientDetailResponse.setBillingAddress1(response.getCustomerInfo().getBillingAddress1());
            clientDetailResponse.setEmail(response.getCustomerInfo().getEmail());
            clientDetailResponse.setBillingPhone(response.getCustomerInfo().getBillingPhone());
            clientDetailResponse.setBillingCity(response.getCustomerInfo().getBillingCity());
            clientDetailResponse.setBillingCountry(response.getCustomerInfo().getBillingCountry());
            clientDetailResponse.setBirthday(response.getCustomerInfo().getBirthday());
            clientDetailResponse.setCreated_at(response.getCustomerInfo().getCreated_at());
        }

        return clientDetailResponse;
    }
}
