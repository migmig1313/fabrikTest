package com.fabrik.test.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
@ToString
public class SaldoResponse implements Serializable {

    public String status;
    public ArrayList<Object> error;
    public Payload payload;
}