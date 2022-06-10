package com.example.migLayout.services;

import com.example.migLayout.entity.Name;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Curl {
    private static final Logger log = LoggerFactory.getLogger(Curl.class);

    public enum HttpMethod {GET, PUT, POST, DELETE}

    private final String endpoint;

    public String getEndpoint() {
        return endpoint;
    }

    private final HttpMethod method;

    public HttpMethod getMethod() {
        return method;
    }

    private final String data;

    public String getData() {
        return data;
    }

    private final Map<String, String> headers;

    public Map<String, String> getHeaders() {
        return headers;
    }

    private Curl(String endpoint, HttpMethod method, String data, Map<String, String> headers) {
        this.endpoint = endpoint;
        this.method = method;
        this.data = data;
        this.headers = headers;
    }

    public String call() throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();

        command.add("curl");
        command.add("-s");
        command.add("-X");
        command.add(method.name());
        command.add("\"" + endpoint + "\"");

        if (headers != null && !headers.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("\"");
            headers.keySet().forEach(s -> builder.append(s).append(":").append(headers.get(s)));
            builder.append("\"");
            command.add("-H");
            command.add(builder.toString());
        }

        if (data != null) {
            command.add("-d");
            command.add("\"" + data + "\"");
//            command.add(data);
        }
        log.info("curl command {}", command.toString());
        return doCurl(command.toArray(new String[0]));
    }

    private String doCurl(String[] args) throws IOException, InterruptedException {
        Process process = new ProcessBuilder(args)
//                .redirectErrorStream(true)
                .start();

        String lines;
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"))) {
//
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
        return lines;
    }


//    private String doCurl(String curlString) throws IOException, InterruptedException {
//        try {
//            java.lang.Process process = Runtime.getRuntime().exec(curlString);
//            System.out.println("curl posted");
//            InputStream inputStream = process.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            System.out.println("output: ");
//            try {
//                Thread.sleep(2000);
//                while (process.isAlive()) Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("return value: " + process.exitValue());
//            reader.lines().forEach(System.out::println);
//            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            reader.lines().forEach(System.err::println);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

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


//package com.example.migLayout.services;
//
//import com.example.migLayout.entity.Name;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//
//public class CurlRunner {
//
//    public curlRequest(){
////        String curlString = "curl -XPOST -H \"Content-Type:application/json\" "
//        String curlString = "curl -XPOST -H \"Content-Type:application/json\" "
//                + "  --data-raw '%s' http://localhost:8080/CRUDaddnames";
//
////        String curlString = "curl -XPOST  \"http://localhost:8080/CRUDaddnames\" " ;
//        System.out.println(String.format(curlString, new Name(name).toJson()));
//        try {
//            java.lang.Process process = Runtime.getRuntime().exec(String.format(curlString, new Name(name).toJson()));
//            System.out.println("curl posted");
//            InputStream inputStream = process.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            System.out.println("output: ");
//            try {
//                Thread.sleep(2000);
//                while (process.isAlive()) Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("return value: " + process.exitValue());
//            reader.lines().forEach(System.out::println);
//            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            reader.lines().forEach(System.err::println);
////            System.out.println("---");
////            System.out.println(process.getInputStream().toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
