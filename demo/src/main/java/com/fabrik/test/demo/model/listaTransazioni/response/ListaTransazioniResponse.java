package com.fabrik.test.demo.model.listaTransazioni;

public class ListaTransazioniResponse {

    public Creditor creditor;
    public String executionDate;
    public String uri;
    public String description;
    public int amount;
    public String currency;
    public boolean isUrgent;
    public boolean isInstant;
    public String feeType;
    public String feeAccountId;
    public TaxRelief taxRelief;
}
    public class Address{
        public Object address;
        public Object city;
        public Object countryCode;
    }

    public class Creditor{
        public String name;
        public Account account;
        public Address address;
    }

    public class LegalPersonBeneficiary{
        public Object fiscalCode;
        public Object legalRepresentativeFiscalCode;
    }

    public class NaturalPersonBeneficiary{
        public String fiscalCode1;
        public Object fiscalCode2;
        public Object fiscalCode3;
        public Object fiscalCode4;
        public Object fiscalCode5;
    }


    public class TaxRelief{
        public String taxReliefId;
        public boolean isCondoUpgrade;
        public String creditorFiscalCode;
        public String beneficiaryType;
        public NaturalPersonBeneficiary naturalPersonBeneficiary;
        public LegalPersonBeneficiary legalPersonBeneficiary;
    }
}
