package com.example.migLayout.services;

public interface BackendClient {
    String createAction(String data);

    String requestAction(String name);

    String updateAction(String data);

    String deleteAction(String name);

    String request(String address,
                   String header1,
                   String header2,
                   String method,
                   String data);

}
