package com.example.migLayout.services.backEndClient;

import com.example.migLayout.entity.Name;

import java.io.IOException;

public interface BackendClient {
    String createAction(Name data);

    String requestAction(String name);

    String updateAction(Name data);

    String deleteAction(String name);

    String request(String address,
                   String header1,
                   String header2,
                   String method,
                   String data);

    enum HttpMethod {GET, PUT, POST, DELETE};

    String call() throws IOException, InterruptedException;

}
