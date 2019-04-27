// Understands how to convert between currencies
public enum Currency {
    DOLLAR(1f, "US Dollar"),
    EURO(1.12f, "Euro");

    private final float inBaseUnits;
    private final String name;

    Currency(float inBaseUnits, String name) {
        this.inBaseUnits = inBaseUnits;
        this.name = name;
    }

    float convertTo(Currency currency, float amount) {
        return amount * inBaseUnits / currency.inBaseUnits;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
