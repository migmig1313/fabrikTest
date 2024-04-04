package com.fabrik.test.demo.controller;

import com.fabrik.test.demo.exceptions.AccountException;
import com.fabrik.test.demo.model.GenericResponse;
import com.fabrik.test.demo.model.effettuaBonificoRequest.MoneyTransferRequest;
import com.fabrik.test.demo.service.FabrickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class api {

    private final Logger logger = LoggerFactory.getLogger(FabrickService.class);

    @Autowired
    private final FabrickService fabrickService;

    public api(FabrickService fabrickService) {
        this.fabrickService = fabrickService;
    }


    @GetMapping("/letturaSaldo")
    public GenericResponse getSaldo(@RequestHeader("Content-Type") MediaType contentType,
                                    @RequestHeader("Auth-Schema") String authSchema,
                                    @RequestHeader("Api-Key") String apiKey,
                                    @RequestParam Long accountId) throws AccountException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", apiKey);
        logger.info("chiamata al service con getSaldo");
        return fabrickService.getSaldo(headers, accountId);
    }

    @PostMapping("/bonifico")
    public GenericResponse createMoneyTransfer(@RequestHeader("Content-Type") MediaType contentType,
                                               @RequestHeader("X-Time-Zone") String xTimeZone,
                                               @RequestHeader("Auth-Schema") String authSchema,
                                               @RequestHeader("Api-Key") String apiKey,
                                               @RequestBody MoneyTransferRequest request) throws AccountException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Time-Zone", xTimeZone);
        headers.setContentType(contentType);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", apiKey);
        logger.info("chiamata al service moneyTransfer");

        return fabrickService.createMoneyTransfer(headers,request);
    }

    @GetMapping("/letturaTransazioni")
    public GenericResponse getAccountTransactions(@RequestHeader("Content-Type") MediaType contentType,
                                                  @RequestHeader("Auth-Schema") String authSchema,
                                                  @RequestHeader("Api-Key") String apiKey,
                                                  @RequestParam Long accountId,
                                                  @RequestParam String fromAccountingDate,
                                                  @RequestParam String toAccountingDate) throws AccountException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        headers.set("Auth-Schema", authSchema);
        headers.set("Api-Key", apiKey);
        logger.info("chiamata al service con getAccountTransactions");
        return fabrickService.getAccountTransactions(headers, accountId,fromAccountingDate,toAccountingDate);
    }

}
