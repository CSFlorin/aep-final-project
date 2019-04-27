// understands balance of a single account
public class BankAccount {

    private float balance;

    BankAccount(float initialDeposit) {
        this.balance = 0f;
        this.deposit(initialDeposit);
    }

    public boolean deposit(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deposit must be at least 0");
        }
        this.balance += amount;
        return true;
    }

    public float getBalance() {
        return this.balance;
    }

    public boolean withdraw(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to withdraw must be at least 0");
        }
        if (this.balance < amount) return false;
        this.balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount destination, float amount) {
        if (this.withdraw(amount)) {
            return destination.deposit(amount);
        }
        return false;
    }

}
