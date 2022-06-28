package com.example.migLayout.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpBackendClient implements BackendClient {
    private static final Logger log = LoggerFactory.getLogger(HttpBackendClient.class);

    public String createAction(String data) {
        String result;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.CREATE))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "HttpClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }

    @Override
    public String requestAction(String name) {
        return null;
    }

    @Override
    public String updateAction(String data) {
        return null;
    }

    @Override
    public String deleteAction(String name) {
        return null;
    }

}
