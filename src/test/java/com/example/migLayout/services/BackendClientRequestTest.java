package com.example.migLayout.services;

import com.example.migLayout.services.backEndClient.BackendClient;
import com.example.migLayout.services.backEndClient.CurlBackendClient;
import com.example.migLayout.services.backEndClient.HttpBackendClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BackendClientRequestTest {
    public BackendClient backendClient;
    BackendClientRequestTest() {
    }

    @BeforeEach
    void setUp() {
        String address = "http://forum.ru-board.com/e.pl";
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Java-http-client/18.0.1.1");
        backendClient = new CurlBackendClient(address,
                BackendClient.HttpMethod.GET,
                null,
                headers);
    }

    @Test
    void request() throws IOException, InterruptedException {

        String responseText1 = "SCRIPT_NAME => /e.pl" +
                "SERVER_NAME => forum.ru-board.com" +
                "SERVER_ADMIN => root@host1" +
                "LD_LIBRARY_PATH => /home/forum/backend/lib" +
                "HTTP_CONNECTION => close" +
                "REQUEST_METHOD => GET" +
                "CONTEXT_PREFIX => " +
                "REQUEST_SCHEME => http" +
                "QUERY_STRING => ";

        String responseText2 = "HTTP_USER_AGENT => Java-http-client/18.0.1.1" +
                "<b>REMOTE_ADDR => 194.143.136.242" +
                "</b>CONTEXT_DOCUMENT_ROOT => /home2/forum/forum.ru-board.com" +
                "SERVER_PROTOCOL => HTTP/1.0" +
                "REQUEST_URI => /e.pl";

        String result=backendClient.call()
                .replace("\n", "")
                .replace("\r", "")
                .replace("HTTP_ACCEPT => */*", "")
                .replace("CONTENT_LENGTH => 0", "")
                .replace("HTTP_HTTP2_SETTINGS => AAEAAEAAAAIAAAABAAMAAABkAAQBAAAAAAUAAEAA", "");
        String result1 = result.substring(
                        0,
                        result.indexOf("REMOTE_PORT"))
                .replaceAll("<br>", "");
        String result2 = result.substring(
                        result.indexOf("HTTP_USER_AGENT"))
                .replaceAll("<br>", "");

        assertEquals(responseText1, result1);
        assertEquals(responseText2, result2);
    }
}