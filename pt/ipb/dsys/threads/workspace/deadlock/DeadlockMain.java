public class DeadlockMain {
    public static void main(String[] args) {
        Account account1 = new Account(100, "James", 1000);
        Account account2 = new Account(567, "Peterson", 500);
        new Thread(() -> AccountManager.transfer(account1, account2, 250)).start();
        new Thread(() -> AccountManager.transfer(account2, account1, 250)).start();
    }
}
