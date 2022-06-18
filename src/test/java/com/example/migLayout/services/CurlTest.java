package com.example.migLayout.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CurlTest {
    private MockWebServer mockWebServer;
    Curl curl;
    Map<String, String> map;
    String data;

    @BeforeEach
    void setUp() {
        this.mockWebServer = new MockWebServer();
        map = new HashMap<>();
        map.put("Content-Type", "application/json");


        curl = new Curl.Builder(mockWebServer.url("/").toString())
                .method(Curl.HttpMethod.POST)
                .headers(map)
                .data(data)
                .create();

    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testRequest() {
        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setResponseCode(500));
        String result = curl.call();

        assertEquals("Lorem ipsum dolor sit amet.", result);
    }
}