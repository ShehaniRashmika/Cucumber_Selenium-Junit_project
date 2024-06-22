package account;

import java.util.HashMap;
import java.util.Map;

public class Account {
    private String accountNumber;
    private String accountName;
    private double balance;
    private Map<String, Double> transactions;

    public Account(String accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.transactions = new HashMap<>();
    }

    public void deposit(String id, double amount) {
        transactions.put(id, amount);
        balance += amount;
    }

    public void withdraw(String id, double amount) {
        transactions.put(id, -amount);
        balance -= amount;
    }

    public void produceStatement() {
        // Produce statement logic (if needed)
    }

    public String getStatement() {
        // Return the statement as a string
        // For simplicity, let's concatenate account details and transactions
        StringBuilder statement = new StringBuilder();
        statement.append("Name: ").append(accountName).append("\n");
        statement.append("Account: ").append(accountNumber).append("\n");
        statement.append("Balance: ").append(balance).append("\n");
        for (Map.Entry<String, Double> entry : transactions.entrySet()) {
            statement.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return statement.toString();
    }

    public Map<String, Double> getTransactions() {
        return transactions;
    }
    
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }
}
