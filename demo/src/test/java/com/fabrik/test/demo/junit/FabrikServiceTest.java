package com.fabrik.test.demo.junit;

import com.fabrik.test.demo.exceptions.AccountException;
import com.fabrik.test.demo.model.GenericResponse;
import com.fabrik.test.demo.model.effettuaBonificoRequest.Account;
import com.fabrik.test.demo.model.effettuaBonificoRequest.Address;
import com.fabrik.test.demo.model.effettuaBonificoRequest.Creditor;
import com.fabrik.test.demo.model.effettuaBonificoRequest.MoneyTransferRequest;
import com.fabrik.test.demo.model.listaTransazioni.response.LegalPersonBeneficiary;
import com.fabrik.test.demo.model.listaTransazioni.response.ListaTransazioniResponse;
import com.fabrik.test.demo.model.listaTransazioni.response.NaturalPersonBeneficiary;
import com.fabrik.test.demo.model.listaTransazioni.response.TaxRelief;
import com.fabrik.test.demo.service.FabrickService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.Utilities;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.ReflectionTestUtils.setField;

class FabrikServiceTest {
    @MockBean
    private FabrickService fabrickService;

    FabrikServiceTest() throws AccountException {
    }

    //I test richiamano il servizio vero e proprio nel FabrikService
    //N.B. Non è stato fatto un mock della response volutamente, in quanto sarebbe fine a se stesso.


    @Test
    public void getsaldoApi_OK() throws AccountException { //Questo servizio risponde correttamente.

        this.fabrickService = new FabrickService(new RestTemplate());
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Long accountId = 14537780L;

        GenericResponse response = fabrickService.getSaldo(headers, accountId);

        //expected
        assertNotNull(response);
        assertEquals("200", response.getStatus());
    }

    //Fatto una prova solo per vedere che il ritorno è quello atteso.
    //In realtà la request non è valorizzata correttamente(anche seguendo la documentazione). Credo dipenda dai dati
    @Test
    public void getsaldoApi_wrong_accountId_403_FORBIDDEN() throws AccountException {

        this.fabrickService = new FabrickService(new RestTemplate());
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Long accountId = 145377L;

        GenericResponse response = fabrickService.getSaldo(headers, accountId);

        //expected
        assertNotNull(response);
        assertEquals("403 FORBIDDEN", response.getStatus());
    }

    @Test
    public void createMoneyTransfer_wrongParameters_error() throws AccountException {

        this.fabrickService = new FabrickService(new RestTemplate());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        MoneyTransferRequest request = new MoneyTransferRequest();
        Creditor creditor = new Creditor();
        String name = "LUCA TERRIBILE";
        Account account = new Account();
        String accountCode = "IT40L0326822311052923800661";
        creditor.setName(name);
        account.setAccountCode(accountCode);
        String description = "prova bonifico";
        String currency = "EUR";
        String amount = "800";
        String executionDate = "2024-04-05";
        request.setCreditor(creditor);
        request.setAmount(accountCode);
        request.setDescription(description);
        request.setCurrency(currency);
        request.setAmount(amount);
        request.setExecutionDate(executionDate);

        GenericResponse response = fabrickService.createMoneyTransfer(headers, request);
        //expected
        assertNotNull(response);
    }


    @Test
    public void getAccountTransactions() throws AccountException {

        this.fabrickService = new FabrickService(new RestTemplate());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        Long accountId = 14537780L;
        String fromAccountingDate = "2019-04-01";
        String toAccountingDate = "2019-12-01";
        GenericResponse response = fabrickService.getAccountTransactions(headers, accountId, fromAccountingDate, toAccountingDate);

        assertNotNull(response); //La response torna una GenericResponse class,e come prova viene popolato il payload. Volendo si può utilizzare l'output di response della documentazione ma il servizio non risponde.
        assertEquals("400 BAD_REQUEST", response.getStatus()); //Si potrebbe fare per i diversi tipi di errori che ritornano
    }

    @Test
    public void getAccountTransactionsUtente_secondoTestConResponseAttesa() throws AccountException {

        FabrickService fabrickService = Mockito.mock(FabrickService.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");

        ListaTransazioniResponse listaTransazioniResponse = new ListaTransazioniResponse();

        Creditor creditor = new Creditor();
        creditor.setName("Luca");

        Account account = new Account();
        account.setAccountCode("IT40L0326822311052923800661");
        account.setBicCode("SELBIT2BXXX");
        creditor.setAccount(account);

        Address address = new Address();
        address.setAddress("address");
        address.setCity("city");
        address.setCity("countryCode");

        String executionDate = "2019-04-01";

        String uri = "REMITTANCE_INFORMATION";
        String description = "Payment invoice 75/2017";
        Integer amount = 800;
        String currency = "EUR";
        Boolean isUrgent = false;
        Boolean isInstant = false;
        String feeType = "SHA";
        String feeAccountId = "45685475";

        TaxRelief taxRelief = new TaxRelief();

        String taxReliefId = "L449";
        Boolean isCondoUpgrade = false;
        String creditorFiscalCode = "56258745832";
        String beneficiaryType = "NATURAL_PERSON";

        taxRelief.setTaxReliefId(taxReliefId);
        taxRelief.setIsCondoUpgrade(isCondoUpgrade);
        taxRelief.setCreditorFiscalCode(creditorFiscalCode);
        taxRelief.setBeneficiaryType(beneficiaryType);


        NaturalPersonBeneficiary naturalPersonBeneficiary = new NaturalPersonBeneficiary();
        naturalPersonBeneficiary.setFiscalCode1("MRLFNC81L04A859L");

        listaTransazioniResponse.setExecutionDate(executionDate);
        listaTransazioniResponse.setUri(uri);
        listaTransazioniResponse.setDescription(description);
        listaTransazioniResponse.setAmount(amount);
        listaTransazioniResponse.setCurrency(currency);
        listaTransazioniResponse.setIsUrgent(isUrgent);
        listaTransazioniResponse.setIsInstant(isInstant);
        listaTransazioniResponse.setFeeType(feeType);
        listaTransazioniResponse.setFeeAccountId(feeAccountId);
        listaTransazioniResponse.setTaxRelief(taxRelief);

        Mockito.when(fabrickService.getAccountTransactionsUtente(Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.anyString())).thenReturn(listaTransazioniResponse);

        assertNotNull(listaTransazioniResponse); //Abbiamo tutilizzato l'output di response della documentazione ma il servizio non risponde.

        ListaTransazioniResponse result = fabrickService.getAccountTransactionsUtente(Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.anyString());

        // Verifico che il sistema sia chiamato correttamente
        Mockito.verify(fabrickService).getAccountTransactionsUtente(Mockito.any(), Mockito.any(), Mockito.anyString(), Mockito.anyString());

        //Controlliamo sempre che non sia null il risultato
        assertNotNull(result);

        //In fine facciamo un mock per vedere che il risultato sia come quello ci saremo dovuti aspettare
        assertEquals(listaTransazioniResponse, result);
    }


}