package com.fabrik.test.demo.model.listaTransazioni.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Creditor{
    public String name;
    public Account account;
    public Address address;
}