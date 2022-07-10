package com.example.migLayout.services;

import com.example.migLayout.entity.Name;
import com.example.migLayout.services.backEndClient.CurlBackendClient;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CrudActions {

    public String createAction(String data) throws IOException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");

        String result;
//        result = new Curl.Builder(Adresses.CREATE)
//                .method(Curl.HttpMethod.POST)
//                .headers(map)
//                .data(data)
//                .create()
//                .call();


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(Adresses.CREATE))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        result = response.toString();
        System.out.println(request);
        System.out.println(response.body());
        return result;
    }

    public String requestAction(String name) throws IOException, InterruptedException {
        String result = new CurlBackendClient.Builder(Adresses.REQUEST + name)
                .method(CurlBackendClient.HttpMethod.GET)
                .create()
                .call();
        return result;
    }

    public String updateAction(String data) throws IOException, InterruptedException {
        String[] idName = data.split(",");
        String result;
        if ("".equals(data) || (idName.length != 2)) {
            JOptionPane.showMessageDialog(null,
                    "InfoBox: " + "Write comma delimited id and name",
                    "Request Error",
                    JOptionPane.INFORMATION_MESSAGE);
            result = "Request Error";
        } else {
            Name name = new Name(idName[1]);
            name.setId(Integer.parseInt(idName[0]));
            Map<String, String> map = new HashMap<>();
            map.put("Content-Type", "application/json");
            result = new CurlBackendClient.Builder(Adresses.UPDATE)
                    .method(CurlBackendClient.HttpMethod.PUT)
                    .headers(map)
                    .data(name.toJson())
                    .create()
                    .call();
        }
        return result;
    }

    public String deleteAction(String name) throws IOException, InterruptedException {
        String result = new CurlBackendClient.Builder(Adresses.DELETE + name)
                .method(CurlBackendClient.HttpMethod.DELETE)
                .create()
                .call();
        return result;
    }
}
