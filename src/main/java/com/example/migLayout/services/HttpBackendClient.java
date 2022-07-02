package com.example.migLayout.services;

import com.example.migLayout.entity.Name;
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
//        String result;
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.CREATE))
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(data))
//                .build();
//        HttpResponse<String> response = null;
//        try {
//            response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            result = response.body();
//        } catch (IOException | InterruptedException e) {
////            e.printStackTrace();
//            result = "Request Error";
//            JOptionPane.showMessageDialog(null,
//                    "InfoBox: " + result,
//                    "HttpClient Error",
//                    JOptionPane.INFORMATION_MESSAGE);
//            log.debug(result);
//        }
//        return result;
        return request(Adresses.CREATE,
                "Content-Type",
                "application/json",
                "POST",
                data);
    }

    @Override
    public String requestAction(String name) {
        String result;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.REQUEST + name))
//                .header("Content-Type", "application/json")
                .GET()
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
    public String updateAction(String data) {
        String[] idName = data.split(",");
        String result;
        if ("".equals(data) || (idName.length != 2)) {
            result = "Id/Name Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "HttpClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            Name name = new Name(idName[1]);
            name.setId(Integer.parseInt(idName[0]));
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.UPDATE))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(name.toJson()))
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
        }
        return result;
    }

    @Override
    public String deleteAction(String name) {
        String result;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.DELETE + name))
                .header("Content-Type", "application/json")
                .DELETE()
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

    public String request(String address,
                           String header1,
                           String header2,
                           String method,
                           String data){
        String result;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(address));
        if (!"".equals(header1) && !"".equals(header2)) {
            builder.header(header1, header2);
        }else{
            log.info ("Request error - wrong header");
        }
        if ("GET".equals(method) || "".equals(method)){
            builder.GET();
        } else if ("POST".equals(method)&& !"".equals(data)){
            builder.POST(HttpRequest.BodyPublishers.ofString(data));
        } else if ("PUT".equals(method)) {
            builder.PUT(HttpRequest.BodyPublishers.ofString(data));
        } else if ("DELETE".equals(method)) {
            builder.DELETE();
        } else {
            log.info ("Request error - wrong method");
        }
        HttpRequest request = builder.build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            result = response.body();
        } catch (IOException | InterruptedException e) {
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "HttpClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }
}
