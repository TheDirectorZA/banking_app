package za.co.ohlukilebanking.server.model;

public class Account {
    private int id;
    private String ownerName;
    private double balance;

    public Account() {}

    public Account(int id, String ownerName, double balance) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
