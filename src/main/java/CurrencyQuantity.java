// Understands an amount in a ratio scale in a given unit
public class CurrencyQuantity implements Bestable<CurrencyQuantity> {
    protected float amount;
    protected final Currency currency;

    public CurrencyQuantity(float amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof CurrencyQuantity)) return false;
        return equals((CurrencyQuantity) other);
    }

    public boolean equals(CurrencyQuantity other) {
        return this.amount == other.convertTo(this.currency);
    }

    protected float convertTo(Currency toCurrency) {
        return this.currency.convertTo(toCurrency, this.amount);
    }

    protected void add(CurrencyQuantity other) {
        this.amount += ((CurrencyQuantity) other).convertTo(this.currency);
    }

    protected void subtract(CurrencyQuantity other) {
        this.amount -= ((CurrencyQuantity) other).convertTo(this.currency);
    }

    @Override
    public int hashCode() {
        return Float.hashCode(amount);
    }

    @Override
    public String toString() {
        return this.amount + " " + this.currency;
    }

    @Override
    public boolean betterThan(CurrencyQuantity other) {
        return this.amount > ((CurrencyQuantity) other).convertTo(this.currency);
    }

}
