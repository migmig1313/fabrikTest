package com.fabrik.test.demo.model.listaTransazioni.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TaxRelief{
    public String taxReliefId;
    public Boolean isCondoUpgrade;
    public String creditorFiscalCode;
    public String beneficiaryType;
    public NaturalPersonBeneficiary naturalPersonBeneficiary;
    public LegalPersonBeneficiary legalPersonBeneficiary;
}