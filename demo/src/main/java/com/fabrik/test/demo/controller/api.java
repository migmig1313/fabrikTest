package com.fabrik.test.demo.controller;

import com.fabrik.test.demo.model.SaldoResponse;
import com.fabrik.test.demo.service.FabrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class api {

    @Autowired
    private final FabrickService fabrickService;

    public api(FabrickService fabrickService) {
        this.fabrickService = fabrickService;
    }

    @GetMapping("/saldoContoCorrente")
        public SaldoResponse getSaldo() {
            return fabrickService.getSaldo();
        }
}
