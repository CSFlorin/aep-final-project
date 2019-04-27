// understands balance of a single account
public class BankAccount {

    private CurrencyQuantity balance;
    private User owner;

    public BankAccount(CurrencyQuantity initialDeposit, User owner) {
        this.balance = new CurrencyQuantity(0f, Currency.DOLLAR);
        this.owner = owner;
        // call deposit to check if invalid amount
        this.deposit(initialDeposit);
    }

    public CurrencyQuantity getBalance(int pin) {
        if (this.owner.verifyPin(pin)) {
            return this.balance;
        }
        throw new RuntimeException("Invalid pin");
    }

    public boolean deposit(CurrencyQuantity amount) {
        if ((new CurrencyQuantity(0f, Currency.DOLLAR)).betterThan(amount)) {
            throw new IllegalArgumentException("Amount to deposit must be at least 0");
        }
        this.balance.add(amount);
        return true;
    }

    public boolean withdraw(CurrencyQuantity amount, int pin) {
        if ((new CurrencyQuantity(0f, Currency.DOLLAR)).betterThan(amount)) {
            throw new IllegalArgumentException("Amount to withdraw must be at least 0");
        }
        else if (!this.owner.verifyPin(pin)) {
            throw new RuntimeException("Invalid pin");
        }
        else if (amount.betterThan(this.balance)) return false;
        else {
            this.balance.subtract(amount);
            return true;
        }
    }

//    public boolean transfer(BankAccount destination, float amount, int pin) {
//        if (this.withdraw(amount, pin)) {
//            return destination.deposit(amount);
//        }
//        return false;
//    }

}
