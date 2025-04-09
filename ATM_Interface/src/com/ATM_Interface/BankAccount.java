package com.ATM_Interface;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.balance = initialBalance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false; // Insufficient funds
        }
        balance -= amount;
        return true; // Withdrawal successful
    }

    public void deposit(double amount) {
        balance += amount; // Deposit successful
    }

    public double getBalance() {
        return balance; // Return current balance
    }
}
