package za.co.ohlukilebanking.client.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientUtils {

    // Utility method to send a GET request and return the response as a string
    public static String sendGetRequest(String requestUrl) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Handle the response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new Exception("GET request failed. Response Code: " + responseCode);
        }
    }

    // Utility method to send a POST request and return the response code
    public static int sendPostRequest(String requestUrl, String requestBody) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        // Send POST request
        try (var os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Handle the response
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            return responseCode;
        } else {
            throw new Exception("POST request failed. Response Code: " + responseCode);
        }
    }

    // Utility method to handle any generic parsing logic (for example, parse JSON)
    public static double parseDoubleResponse(String response) throws Exception {
        try {
            return Double.parseDouble(response);
        } catch (NumberFormatException e) {
            throw new Exception("Failed to parse response as a double: " + response);
        }
    }
}
