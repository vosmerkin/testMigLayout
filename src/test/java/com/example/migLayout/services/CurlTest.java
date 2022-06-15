package com.example.migLayout.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurlTest {
    Curl curl;
    Curl.Builder curlBuilder;

    @BeforeEach
    void setUp() {
        String result = new Curl.Builder(Adresses.CREATE)
                .method(Curl.HttpMethod.POST)
                .headers(map)
                .data(data)
                .create()
                .call();
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testRequest() {

    }
}