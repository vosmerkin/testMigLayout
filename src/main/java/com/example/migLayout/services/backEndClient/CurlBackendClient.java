package com.example.migLayout.services.backEndClient;

import com.example.migLayout.entity.Name;
import com.example.migLayout.services.Adresses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class CurlBackendClient implements BackendClient {

    private static final Logger log = LoggerFactory.getLogger(CurlBackendClient.class);

    //    private final String endpoint;
//    private final HttpMethod method;
//    private final String data;
//    private final Map<String, String> headers;
    private CurlClient curlClient;

    public CurlBackendClient() {
//        this.endpoint = null;
//        this.method = null;
//        this.data = null;
//        this.headers = null;
    }

    public CurlBackendClient(String endpoint, HttpMethod method, String data, Map<String, String> headers) {
//        this.endpoint = endpoint;
//        this.method = method;
//        this.data = data;
//        this.headers = headers;
        curlClient = new CurlClient.Builder(endpoint)
                .method(CurlClient.HttpMethod.valueOf(method.name()))
                .headers(headers)
                .data(data)
                .create();
    }


    public String call() throws IOException, InterruptedException {
        return curlClient.call();
    }


    public String request(String address,
                          String header1,
                          String header2,
                          String method,
                          String data) {
        CurlClient.Builder builder = new CurlClient.Builder(address);
        String result;

        if (!"".equals(header1) && !"".equals(header2)) {
            Map<String, String> map = new HashMap<>();
            map.put(header1, header2);
            builder.headers(map);
        } else if ("".equals(header1) && !"".equals(header2) || !"".equals(header1)) {
            log.info("Request error - wrong header");
        }
        if ("GET".equals(method) || "".equals(method)) {
            builder.method(CurlClient.HttpMethod.GET);
        } else if ("POST".equals(method)) {
            builder.method(CurlClient.HttpMethod.POST);
        } else if ("PUT".equals(method)) {
            builder.method(CurlClient.HttpMethod.PUT);
        } else if ("DELETE".equals(method)) {
            builder.method(CurlClient.HttpMethod.DELETE);
        } else {
            log.info("Request error - wrong method");
        }
        if (!"".equals(data)) {
            builder.data(data);
        }
        curlClient = builder.create();
        try {
            result = curlClient.call();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "CurlClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }

    @Override
    public String createAction(Name data) {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        String result;

        curlClient = new CurlClient.Builder(Adresses.CREATE)
                .method(CurlClient.HttpMethod.POST)
                .headers(map)
                .data(data.toJson())
                .create();
        try {
            result = curlClient.call();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "CurlClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }

    public String requestAction(String name) {
        String result;
        curlClient = new CurlClient.Builder(Adresses.REQUEST + name)
                .method(CurlClient.HttpMethod.GET)
                .create();
        try {
            result = curlClient.call();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "CurlClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }

    public String updateAction(Name data) {
        String result;
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");
        curlClient = new CurlClient.Builder(Adresses.UPDATE)
                .method(CurlClient.HttpMethod.PUT)
                .headers(map)
                .data(data.toJson())
                .create();
        try {
            result = curlClient.call();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "CurlClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }

    public String deleteAction(String name) {
        String result;
        curlClient = new CurlClient.Builder(Adresses.DELETE + name)
                .method(CurlClient.HttpMethod.DELETE)
                .create();
        try {
            result = curlClient.call();
        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
            result = "Request Error";
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + result,
                    "CurlClient Error",
                    JOptionPane.INFORMATION_MESSAGE);
            log.debug(result);
        }
        return result;
    }


}