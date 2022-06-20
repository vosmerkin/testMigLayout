package com.example.migLayout.services;

import com.example.migLayout.entity.Name;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;

public class CrudActions {

    public String createAction(String data ) throws IOException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json");

        String result = new Curl.Builder(Adresses.CREATE)
                .method(Curl.HttpMethod.POST)
                .headers(map)
                .data(data)
                .create()
                .call();



        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(
                        URI.create("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY"))
                .header("accept", "application/json")
                .build();

        var response = client.send(request, new JsonBodyHandler<>(APOD.class));
        client
        return result;
    }

    public String requestAction(String name) throws IOException, InterruptedException {
        String result = new Curl.Builder(Adresses.REQUEST + name)
                .method(Curl.HttpMethod.GET)
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
            result = new Curl.Builder(Adresses.UPDATE)
                    .method(Curl.HttpMethod.PUT)
                    .headers(map)
                    .data(name.toJson())
                    .create()
                    .call();
        }
        return result;
    }

    public String deleteAction(String name) throws IOException, InterruptedException {
        String result = new Curl.Builder(Adresses.DELETE + name)
                .method(Curl.HttpMethod.DELETE)
                .create()
                .call();
        return result;
    }
}
