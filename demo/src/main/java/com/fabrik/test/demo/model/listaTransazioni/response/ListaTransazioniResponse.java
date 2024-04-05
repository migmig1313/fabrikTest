package com.fabrik.test.demo.model.listaTransazioni.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ListaTransazioniResponse {

    public Creditor creditor;
    public String executionDate;
    public String uri;
    public String description;
    public Integer amount;
    public String currency;
    public Boolean isUrgent;
    public Boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public TaxRelief taxRelief;
}

