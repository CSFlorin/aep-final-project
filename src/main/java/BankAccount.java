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
}
