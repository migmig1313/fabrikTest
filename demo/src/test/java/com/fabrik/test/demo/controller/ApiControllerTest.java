package com.fabrik.test.demo.controller;

import com.fabrik.test.demo.model.GenericResponse;
import com.fabrik.test.demo.exceptions.AccountException;
import com.fabrik.test.demo.service.FabrickService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApiControllerTest {
    @MockBean
    private FabrickService fabrickService;

    @InjectMocks
    private ApiControllerTest apiController;

    @Test
    public void getsaldoAPI_OK() throws AccountException {

        this.fabrickService = new FabrickService(new RestTemplate());
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Auth-Schema", "S2S");
        headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
        Long accountId = 14537780L;

        /*//when
        SaldoResponse mockedResponse = new SaldoResponse();
        Payload payload = new Payload();
        payload.setBalance(-19.86);
        mockedResponse.setStatus("OK");;
        mockedResponse.setPayload(payload);//*/

        GenericResponse response = fabrickService.getSaldo(headers,accountId);

        //expected
        assertNotNull(response);
        assertEquals("200", response.getStatus());
        //assertEquals(-19.86,((SaldoResponse) response.getPayload()).getPayload().getAvailableBalance());

    }
}