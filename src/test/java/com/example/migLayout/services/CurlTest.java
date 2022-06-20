package com.example.migLayout.services;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurlTest {
    private MockWebServer mockWebServer;
    Curl curl;
    Curl.Builder builder;
    Map<String, String> map;
    String data;

    @BeforeEach
    void setUp() {
        this.mockWebServer = new MockWebServer();


    }

    @Test
    @DisplayName("Test create operation")
//    curl -XPOST -H "Content-Type:application/json"   --data-raw "{\"id\" : null,\"name\" : \"Peter1\"}" http://localhost:8080/CRUDaddnames

    void testCreate() {
        String responseText ="{\"id\":15," +
                "\"name\":\"Peter1\"," +
                "\"carId\":null," +
                "\"salaryId\":null," +
                "\"car\":null," +
                "\"salary\":null}";
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .setBody(responseText));

        map = new HashMap<>();
        map.put("Content-Type", "application/json");

        data = "{\"id\" : null,\"name\" : \"Peter1\"}";

        curl = new Curl.Builder(mockWebServer.url("/").toString())
                .method(Curl.HttpMethod.PUT)
                .headers(map)
                .data(data)
                .create();


        String result = null;
        try {
            result = curl.call();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(responseText, result);
    }
}