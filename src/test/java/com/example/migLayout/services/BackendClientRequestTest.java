package com.example.migLayout.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;

import static org.junit.jupiter.api.Assertions.*;

class BackendClientRequestTest {
    public BackendClient backendClient;
    private String address;
    private String header1;
    private String header2;
    private String method;
    private String data;

    @BeforeEach
    void setUp() {
        backendClient = new HttpBackendClient();
        address="http://forum.ru-board.com/e.pl";
        header1="";
        header2="";
        method="GET";
c    }

    @Test
    void request() {

        String result = backendClient.request(
                address,
                header1,
                header2,
                method,
                data

        )
    }
}