package com.example.migLayout.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Curl {
    private static final Logger log = LoggerFactory.getLogger(Curl.class);

    public enum HttpMethod {GET, PUT, POST, DELETE}

    private final String endpoint;

    private final HttpMethod method;

    private final String data;
    private final Map<String, String> headers;

    private Curl(String endpoint, HttpMethod method, String data, Map<String, String> headers) {
        this.endpoint = endpoint;
        this.method = method;
        this.data = data;
        this.headers = headers;
    }


    public String getEndpoint() {
        return endpoint;
    }


    public HttpMethod getMethod() {
        return method;
    }


    public String getData() {
        return data;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }


    public String call() throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();

        command.add("curl");
        command.add("-s");
        command.add("-X");
        command.add(method.name());
//        command.add("\"");
        command.add(endpoint);
//        command.add("\"");

        if (headers != null && !headers.isEmpty()) {
            StringBuilder builder = new StringBuilder();
//            builder.append("\"");
            headers.keySet().forEach(s -> builder.append(s).append(":").append(headers.get(s)));
//            builder.append("\"");
            command.add("-H");
            command.add(builder.toString());
        }

        if (data != null) {
            command.add("-d");
//            command.add("\"" + data + "\"");
            command.add(data);
        }
        log.info("curl command {}", command);
        return doCurl(command.toArray(new String[0]));
    }

    private String doCurl(String[] args) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(args)
//                .redirectErrorStream(true)
                .start();
        String lines;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
//            lines = reader.lines().collect(Collectors.joining("\n"));
//        }
//        process.waitFor();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder responseStrBuilder = new StringBuilder();

        while ((lines = br.readLine()) != null) {
            System.out.println("read line from curl command: " + lines);
            responseStrBuilder.append(lines);
        }
        return responseStrBuilder.toString();
    }

    public static class Builder {

        private String endpoint;
        private HttpMethod method;
        private String data;
        private Map<String, String> headers;

        public Builder(String endpoint) {
            this.endpoint = endpoint;
        }

        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder data(String data) {
            this.data = data;
            return this;
        }

        public Builder headers(Map<String, String> headers) {
            this.headers = headers;
            return this;
        }

        public Curl create() {
            if (endpoint == null) {
                throw new IllegalArgumentException("Endpoint cannot be null");
            }
            if (method == null) {
                throw new IllegalArgumentException("HTTP method cannot be null");
            }
            return new Curl(endpoint, method, data, headers);
        }
    }
}