package za.co.ohlukilebanking.client;

import za.co.ohlukilebanking.client.service.BankClient;

import java.util.Scanner;

public class ClientApp {
    public static void main(String[] args) {
        BankClient bankClient = new BankClient();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Banking Application:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter account ID: ");
                    int accountId = scanner.nextInt();
                    System.out.println("Balance: " + bankClient.getBalance(accountId));
                    break;
                case 2:
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankClient.deposit(accountId, depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter account ID: ");
                    accountId = scanner.nextInt();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankClient.withdraw(accountId, withdrawAmount);
                    System.out.println("Withdrawal successful.");
                    break;
                case 4:
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
