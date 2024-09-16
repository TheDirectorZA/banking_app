package za.co.ohlukilebanking.server;

import io.javalin.Javalin;
import za.co.ohlukilebanking.server.api.AccountController;

public class ServerApp {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        AccountController accountController = new AccountController();

        app.get("/api/accounts/:id/balance", accountController::getBalance);
        app.post("/api/accounts/:id/deposit", accountController::deposit);
        app.post("/api/accounts/:id/withdraw", accountController::withdraw);

        System.out.println("Server running on http://localhost:7000");
    }
}
