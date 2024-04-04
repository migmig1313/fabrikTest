package com.fabrik.test.demo.service;

import com.fabrik.test.demo.exceptions.AccountException;
import com.fabrik.test.demo.model.GenericResponse;
import com.fabrik.test.demo.model.effettuaBonificoRequest.MoneyTransferRequest;
import com.fabrik.test.demo.model.effettuaBonificoResponse.MoneyTransferResponse;
import com.fabrik.test.demo.model.getSaldoResponse.SaldoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FabrickService {
    private final Logger logger = LoggerFactory.getLogger(FabrickService.class);

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://sandbox.platfr.io";

    public FabrickService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GenericResponse getSaldo(HttpHeaders headers, Long accountId) throws AccountException {

        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        //String accountId = "14537780";
        url = url.replace("{accountId}", String.valueOf(accountId));
/*
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
*/
        ResponseEntity<SaldoResponse> response = null;
        try {
            logger.info("chiamata url esterna GET");
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), SaldoResponse.class, accountId, headers);
        } catch (HttpStatusCodeException e) {
            logger.error("Errore durante la chiamata all'API di Fabrick: {}", e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new GenericResponse(e.getStatusCode().toString(), errors, null);
        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
        return new GenericResponse("200", null, response.getBody().getPayload());
    }

    public GenericResponse createMoneyTransfer(HttpHeaders headers, MoneyTransferRequest request) throws AccountException {


        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
        Long accountId = 14537780L; //TODO STUB Da rimuovere
        url = url.replace("{accountId}", String.valueOf((accountId)));

        ResponseEntity<MoneyTransferResponse> response = null;

        try {
            logger.info("chiamata url esterna POST");
            response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, headers), MoneyTransferResponse.class, request, headers);
        } catch (HttpStatusCodeException e) {
            logger.error("Errore durante la chiamata all'API di Fabrick: {}", e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new GenericResponse(e.getStatusCode().toString(), errors, null);
        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
        return new GenericResponse("200", null, response.getBody().getPayload());
    }

    public GenericResponse getAccountTransactions(HttpHeaders headers, Long accountId, String fromAccountingDate, String toAccountingDate) throws AccountException {


        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/{accountId}/transactions[?<uriQuery>]";
        //String accountId = "14537780";
        String queryString = "fromAccountingDate=" + fromAccountingDate + "&toAccountingDate=" + toAccountingDate;
        url = url.replace("{accountId}", String.valueOf(accountId))
                .replace("[?<uriQuery>]", "?" + queryString);
/*
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
*/
        ResponseEntity<SaldoResponse> response = null;
        try {
            logger.info("chiamata url esterna GET");
            response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), SaldoResponse.class, accountId, headers, fromAccountingDate, toAccountingDate);
        } catch (HttpStatusCodeException e) {
            logger.error("Errore durante la chiamata all'API di Fabrick: {}", e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new GenericResponse(e.getStatusCode().toString(), errors, null);
        } catch (Exception e) {
            throw new AccountException(e.getMessage());
        }
        return new GenericResponse("200", null, response.getBody().getPayload());
    }
}
