// understands balance of a user
public class BankAccount {

    int balance;

    BankAccount(int initialDeposit) {
        this.balance = 0;
        this.deposit(initialDeposit);
    }

    public boolean deposit(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deposit must be at least 0");
        }
        this.balance += amount;
        return true;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean withdraw(int amount) {
        if (this.balance < amount) return false;
        this.balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount destination, int amount) {
        if (this.withdraw(amount)) {
            return destination.deposit(amount);
        }
        return false;
    }
}
