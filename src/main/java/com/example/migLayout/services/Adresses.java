package com.example.migLayout.services;

public class Adresses {

    private static String host1 = "http://192.168.31.35:8080";
    private static String host2 = "http://localhost:8080";
    private static String host = host2;
    public static final String CREATE = host + "/CRUDaddnames";
    public static final String REQUEST = host + "/CRUDgetnames?name=";
    public static final String UPDATE = host + "/CRUDupdatenames";
    public static final String DELETE = host + "/CRUDdeletenames?name=";
}