package bankAccount;

import java.util.Random;

public class BankAccount {
    private int amount = 1000;

    public synchronized void deposit(int amount) {

        this.amount += amount;

        System.out.println("Amount deposit：" + amount);

    }

    public synchronized void withdraw(int amount) {

        if (this.amount - amount < 0) {
            System.out.println("Amount is not enough! ");
            return;
        }
        this.amount -= amount;

        System.out.println("Amount withdraw：" + amount);

    }

    public synchronized int getBalance() {
        return this.amount;
    }

    public static void main(String[] args) throws InterruptedException {

        final BankAccount bank = new BankAccount();
        Random random = new Random();
        int gotoBankTimes = 4;
        for (int i = 0; i < gotoBankTimes; i++) {

            Thread tadd = new Thread(new Runnable() {

                @Override
                public void run() {
                    bank.deposit(random.nextInt(bank.amount));
                    System.out.println("After deposit Balance: " + bank.getBalance());

                }
            });

            Thread tsub = new Thread(new Runnable() {

                @Override
                public void run() {
                    bank.withdraw(random.nextInt(bank.amount));
                    System.out.println("After withdraw Balance: " + bank.getBalance());
                }
            });
            tsub.start();
            tsub.join();
            tadd.start();
            tadd.join();
        }
    }

}
