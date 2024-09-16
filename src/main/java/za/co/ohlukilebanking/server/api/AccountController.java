package za.co.ohlukilebanking.server.api;

import io.javalin.http.Context;
import za.co.ohlukilebanking.server.service.BankService;

public class AccountController {
    private BankService bankService = new BankService();

    public void getBalance(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("id"));
        double balance = bankService.getBalance(accountId);
        ctx.json(balance);
    }

    public void deposit(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("id"));
        double amount = Double.parseDouble(ctx.body());
        bankService.deposit(accountId, amount);
        ctx.status(200);
    }

    public void withdraw(Context ctx) {
        int accountId = Integer.parseInt(ctx.pathParam("id"));
        double amount = Double.parseDouble(ctx.body());
        bankService.withdraw(accountId, amount);
        ctx.status(200);
    }
}
