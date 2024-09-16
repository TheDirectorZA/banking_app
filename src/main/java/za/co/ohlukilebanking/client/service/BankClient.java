package za.co.ohlukilebanking.client.service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BankClient {
    private final String BASE_URL = "http://localhost:7000/api/accounts";

    public double getBalance(int accountId) {
        try {
            URL url = new URL(BASE_URL + "/" + accountId + "/balance");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner scanner = new Scanner(connection.getInputStream());
            return scanner.nextDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void deposit(int accountId, double amount) {
        sendRequest(accountId, amount, "/deposit");
    }

    public void withdraw(int accountId, double amount) {
        sendRequest(accountId, amount, "/withdraw");
    }

    private void sendRequest(int accountId, double amount, String action) {
        try {
            URL url = new URL(BASE_URL + "/" + accountId + action);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String jsonInputString = String.valueOf(amount);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
