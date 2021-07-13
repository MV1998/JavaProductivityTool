package com.viriminfotech.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class InternetConnectionChecker {

    public boolean isInternetAvailable() {
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
            return true;
        } catch (MalformedURLException e) {
            System.out.println("Maybe internet issue:");
        } catch (IOException e) {
            System.out.println("Maybe internet issue:");
        }
        return true;
    }
}
