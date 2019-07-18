package com.nikitiuk.weatherstatusanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Retriever {
    private static final String newLine  = System.getProperty("line.separator");
    public static void main(String[] args) {
        //testing html retrieval
        try  {
            URL url = new URL("https://www.foreca.com/Ukraine/Odesa");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            String contentType = con.getContentType();
            System.out.println(contentType);
            String readStream = readStream(con.getInputStream());
            // Give output for the command line
            System.out.println(readStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine + newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
