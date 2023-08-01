package com.example.restclienttest;

import com.example.restclienttest.EchoClient.EchoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(EchoClient.class)
class EchoClientTest {

    @Autowired
    EchoClient subject;

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void echo() throws JsonProcessingException {
        String name = "miguel";
        server.expect(requestTo(URI.create("https://postman-echo.com/get?name=miguel")))
                .andExpect(header("cookie", "cookie-value"))
                .andRespond(withSuccess(createResponse(name), MediaType.APPLICATION_JSON));

        final String echoName = subject.echo(name);

        assertEquals(name, echoName);
    }

    private String createResponse(String name) throws JsonProcessingException {
        final EchoResponse response = new EchoResponse();
        response.setArgs(Map.of("name", name));
        return objectMapper.writeValueAsString(response);
    }
}