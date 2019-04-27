// understands balance of a user
public class BankAccount {

    int balance;

    BankAccount(int initialDeposit) {
        this.balance = initialDeposit;
    }

    boolean deposit(int amount) {
        this.balance += amount;
        return true;
    }

    int getBalance() {
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
