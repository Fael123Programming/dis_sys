public class AccountManager {
    static void transfer(Account from, Account to, double amount) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
        // Always lock the account that has the lowest number.
        // The only way for avoiding deadlock is giving privilege 
        // based on certain criteria to a thread to be locked.
        Account a1 = from, a2 = to;
        if (to.getNumber() < from.getNumber()) {
            a1 = to;
            a2 = from;
        }
        synchronized(a1) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            if (from.withdraw(amount)) {
                System.out.println("> Withdrew $" + amount + " from account " + from);
            }
            synchronized(a2) {
                if (to.deposit(amount)) {
                    System.out.println("> Deposited $" + amount + " to account " + to);
                }
            }
        }
    }
}
