package za.co.ohlukilebanking.server.service;

import za.co.ohlukilebanking.server.model.Account;
import za.co.ohlukilebanking.server.repository.AccountRepository;

public class BankService {
    private AccountRepository accountRepository = new AccountRepository();

    public double getBalance(int accountId) {
        Account account = accountRepository.getAccountById(accountId);
        return account.getBalance();
    }

    public void deposit(int accountId, double amount) {
        Account account = accountRepository.getAccountById(accountId);
        account.setBalance(account.getBalance() + amount);
        accountRepository.updateAccount(account);
    }

    public void withdraw(int accountId, double amount) {
        Account account = accountRepository.getAccountById(accountId);
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            accountRepository.updateAccount(account);
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }
}
