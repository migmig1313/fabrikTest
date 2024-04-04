package com.fabrik.test.demo.model.getSaldoResponse;

import com.fabrik.test.demo.model.Payload;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SaldoResponse {

    private String status;
    private List<Object> error;
    private Payload payload;
}