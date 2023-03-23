public class Account {
    private int number;
    private String name;
    private double balance;

    public Account(int number, String name, double balance) {
        this.number = number;
        this.name = name;
        this.balance = balance;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount > 0) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[number=%d, owner=%s, amount=%.2f]", number, name, balance);
    }
}
