package com.fabrik.test.demo.service;

import com.fabrik.test.demo.model.SaldoResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FabrickService {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://sandbox.platfr.io";

    public FabrickService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SaldoResponse getSaldo() {

        String url = baseUrl + "/api/gbs/banking/v4.0/accounts/{accountId}/balance";
        String accountId = "14537780";
        url = url.replace("{accountId}", accountId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        ResponseEntity<SaldoResponse> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), SaldoResponse.class);
        return response.getBody();
    }
}
