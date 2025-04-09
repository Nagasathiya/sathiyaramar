package com.ATM_Interface;


public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.00); // Example initial balance
        ATM atm = new ATM(account);
        atm.showMenu();
    }
}

