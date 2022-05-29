package com.example.migLayout.services;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DownloadUrl {
    private static final Logger log =  LoggerFactory.getLogger(DownloadUrl.class);
    public static final String CONNECTION_USERAGENT_STRING = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101";



    public String getByUrl(String url) throws IOException {
        log.debug("Downloading  {}", url);
        Connection conn = Jsoup.connect(url).userAgent(CONNECTION_USERAGENT_STRING).ignoreContentType(true);
        conn.execute();
        return conn.get().toString();
    }
}
