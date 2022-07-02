package com.example.migLayout.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.PrivateKey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BackendClientRequestTest {
    public BackendClient backendClient;
    private String address;
    private String header1;
    private String header2;
    private String method;
    private String data;
    private String responseText1;
    private String responseText2;

    @BeforeEach
    void setUp() {
        backendClient = new HttpBackendClient();
        address = "http://forum.ru-board.com/e.pl";
        header1 = "User-Agent";
        header2 = "Java-http-client/18.0.1.1";
        method = "GET";

    }

    @Test
    void request() {

        responseText1 = "SCRIPT_NAME => /e.pl" +
                "SERVER_NAME => forum.ru-board.com" +
                "SERVER_ADMIN => root@host1" +
                "LD_LIBRARY_PATH => /home/forum/backend/lib" +
                "HTTP_CONNECTION => close" +
                "REQUEST_METHOD => GET" +
                "CONTEXT_PREFIX => " +
                "REQUEST_SCHEME => http" +
                "QUERY_STRING => ";

        responseText2 = "HTTP_USER_AGENT => Java-http-client/18.0.1.1" +
                "<b>REMOTE_ADDR => 194.143.136.242" +
                "</b>CONTEXT_DOCUMENT_ROOT => /home2/forum/forum.ru-board.com" +
                "SERVER_PROTOCOL => HTTP/1.0" +
                "REQUEST_URI => /e.pl";

        String result = backendClient.request(
                address,
                header1,
                header2,
                method,
                data)
                .replace("\n", "")
                .replace("\r", "")
                .replace("HTTP_ACCEPT => */*", "")
                .replace("CONTENT_LENGTH => 0", "")
                .replace("HTTP_HTTP2_SETTINGS => AAEAAEAAAAIAAAABAAMAAABkAAQBAAAAAAUAAEAA", "");
        String result1 = result.substring(
                0,
                result.indexOf("REMOTE_PORT") )
                .replaceAll("<br>","");
        String result2 = result.substring(
                result.indexOf("HTTP_USER_AGENT"))
                .replaceAll("<br>","");

        assertEquals(responseText1, result1);
        assertEquals(responseText2, result2);
    }
}