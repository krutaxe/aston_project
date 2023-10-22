package ru.aston.nikolaev.hometask5;

public class Main {
    public static BankTransactions bankTransactions = new BankTransactions();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    bankTransactions.transfer(1, 2, 1);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    bankTransactions.transfer(2, 3, 1);

                }
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {

                    bankTransactions.transfer(3, 1, 1);
                }
            }
        });

        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {

                }
            }
        });

        Thread thread5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {

                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
//        thread4.start();
//        thread5.start();

    }
}
