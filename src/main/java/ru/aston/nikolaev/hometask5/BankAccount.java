package ru.aston.nikolaev.hometask5;

import java.util.StringJoiner;

public class BankAccount {
    private int id;

    private int amount;

    public BankAccount(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public BankAccount() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BankAccount.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("amount=" + amount)
                .toString();
    }
}
