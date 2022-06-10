package com.example.migLayout.services;

import java.io.IOException;
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

        return result;
    }
}
