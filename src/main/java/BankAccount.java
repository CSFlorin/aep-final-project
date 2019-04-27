// understands balance of a single account
public class BankAccount {

    private float balance;
    private User owner;

    BankAccount(float initialDeposit, User owner) {
        this.balance = 0f;
        this.owner = owner;
        this.deposit(initialDeposit);
    }

    public boolean deposit(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to deposit must be at least 0");
        }
        this.balance += amount;
        return true;
    }

    public float getBalance(int pin) {
        if (this.owner.verifyPin(pin)) {
            return this.balance;
        }
        throw new RuntimeException("Invalid pin");
    }

    public boolean withdraw(float amount, int pin) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount to withdraw must be at least 0");
        }
        else if (!this.owner.verifyPin(pin)) {
            throw new RuntimeException("Invalid pin");
        }
        else if (this.balance < amount) return false;
        else {
            this.balance -= amount;
            return true;
        }
    }

    public boolean transfer(BankAccount destination, float amount, int pin) {
        if (this.withdraw(amount, pin)) {
            return destination.deposit(amount);
        }
        return false;
    }

}
