package com.archobyte.SimpleMcRecords.Web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebApi {
    public static WebApiResponse createRequest(String targetURL, RequestMethod method, String requestBody) throws IOException {
        URL url = new URL(targetURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method.toString());
        if (requestBody != null) {      //method != RequestMethod.GET && method != RequestMethod.DELETE
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            // Send request to an API
            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(requestBody);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        // Get response from API
        int status = conn.getResponseCode();
        Reader reader = null;

        if (status > 299)
            reader = new InputStreamReader(conn.getErrorStream());
        else
            reader = new InputStreamReader(conn.getInputStream());

        StringBuilder responseBody = new StringBuilder();
        // Read response from API
        try (BufferedReader bfReader = new BufferedReader(reader)) {
            String line;
            while ((line = bfReader.readLine()) != null) {
                responseBody.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return new WebApiResponse(status, responseBody.toString());
    }
}
