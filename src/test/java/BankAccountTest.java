import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    int jezPin = 1234;
    User jez = new User(jezPin);

    int florinPin = 5678;
    User florin = new User(florinPin);

    @Test
    public void canCreateBankAccount() {
        CurrencyQuantity zeroDollars = new CurrencyQuantity(0f, Currency.DOLLAR);
        BankAccount a = new BankAccount(zeroDollars, jez);
    }

    @Test
    public void canDeposit() {
        CurrencyQuantity twentyDollars = new CurrencyQuantity(20f, Currency.DOLLAR);
        CurrencyQuantity tenDollars = new CurrencyQuantity(10f, Currency.DOLLAR);
        CurrencyQuantity thirtyDollars = new CurrencyQuantity(30f, Currency.DOLLAR);

        BankAccount a = new BankAccount(twentyDollars, jez);
        assertTrue(a.deposit(tenDollars));
        assertEquals(thirtyDollars, a.getBalance(jezPin));
    }

    @Test
    public void canWithdraw() {
        CurrencyQuantity tenDollars = new CurrencyQuantity(10f, Currency.DOLLAR);
        CurrencyQuantity sixDollars = new CurrencyQuantity(6f, Currency.DOLLAR);
        CurrencyQuantity fourDollars = new CurrencyQuantity(4f, Currency.DOLLAR);
        BankAccount a = new BankAccount(tenDollars, jez);
        assertTrue(a.withdraw(fourDollars, jezPin));
        assertEquals(sixDollars, a.getBalance(jezPin));
    }

    @Test
    public void cannotOverdraw() {
        CurrencyQuantity tenDollars = new CurrencyQuantity(10f, Currency.DOLLAR);
        CurrencyQuantity elevenDollars = new CurrencyQuantity(11f, Currency.DOLLAR);
        BankAccount a = new BankAccount(tenDollars, jez);
        assertFalse(a.withdraw(elevenDollars, jezPin));
        assertEquals(tenDollars, a.getBalance(jezPin));
    }

    @Test
    public void jezCanTransferToAnotherOfHisAccounts() {
        CurrencyQuantity oneHundredDollars = new CurrencyQuantity(100f, Currency.DOLLAR);
        CurrencyQuantity fiftyDollars = new CurrencyQuantity(50f, Currency.DOLLAR);
        CurrencyQuantity oneHundredFiftyDollars = new CurrencyQuantity(150f, Currency.DOLLAR);
        BankAccount a = new BankAccount(oneHundredDollars, jez);
        BankAccount b = new BankAccount(oneHundredDollars, jez);
        assertTrue(a.transfer(b, fiftyDollars, jezPin));
        assertEquals(fiftyDollars, a.getBalance(jezPin));
        assertEquals(oneHundredFiftyDollars, b.getBalance(jezPin));
    }

    @Test
    public void cannotTransferMoreThanBalance() {
        CurrencyQuantity oneHundredDollars = new CurrencyQuantity(100f, Currency.DOLLAR);
        CurrencyQuantity oneHundredOneDollars = new CurrencyQuantity(101f, Currency.DOLLAR);
        BankAccount a = new BankAccount(oneHundredDollars, jez);
        BankAccount b = new BankAccount(oneHundredDollars, jez);
        assertFalse(a.transfer(b, oneHundredOneDollars, jezPin));
        assertEquals(oneHundredDollars, a.getBalance(jezPin));
        assertEquals(oneHundredDollars, b.getBalance(jezPin));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDepositNegativeAmount() {
        CurrencyQuantity negativeOneHundredDollars = new CurrencyQuantity(-100f, Currency.DOLLAR);
        BankAccount a = new BankAccount(negativeOneHundredDollars, jez);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotWithdrawNegativeAmount() {
        CurrencyQuantity zeroDollars = new CurrencyQuantity(0f, Currency.DOLLAR);
        CurrencyQuantity negativeOneHundredDollars = new CurrencyQuantity(-100f, Currency.DOLLAR);
        BankAccount a = new BankAccount(zeroDollars, jez);
        a.withdraw(negativeOneHundredDollars, jezPin);
    }

    @Test(expected = RuntimeException.class)
    public void cannotGetBalanceWithInvalidPin() {
        CurrencyQuantity oneHundredDollars = new CurrencyQuantity(100f, Currency.DOLLAR);
        BankAccount a = new BankAccount(oneHundredDollars, jez);
        a.getBalance(1235);
    }

    @Test
    public void jezCannotWithdrawFromFlorinsAccount() {
        CurrencyQuantity oneHundredDollars = new CurrencyQuantity(100f, Currency.DOLLAR);
        CurrencyQuantity tenDollars = new CurrencyQuantity(10f, Currency.DOLLAR);
        BankAccount a = new BankAccount(oneHundredDollars, jez);
        BankAccount b = new BankAccount(oneHundredDollars, florin);
        try {
            b.withdraw(tenDollars, jezPin);
            fail("Jez allowed to withdraw");
        } catch (RuntimeException e) {
            assertEquals(oneHundredDollars, b.getBalance(florinPin));
            assertEquals(oneHundredDollars, a.getBalance(jezPin));
        }
    }

    @Test
    public void canWithdrawEuros() {
        CurrencyQuantity oneHundredDollars = new CurrencyQuantity(100f, Currency.DOLLAR);
        CurrencyQuantity ninetyEightPointEightyEightDollars = new CurrencyQuantity(98.88f, Currency.DOLLAR);
        CurrencyQuantity oneEuro = new CurrencyQuantity(1f, Currency.EURO);
        BankAccount a = new BankAccount(oneHundredDollars, jez);
        a.withdraw(oneEuro, jezPin);
        assertEquals(ninetyEightPointEightyEightDollars, a.getBalance(jezPin));
    }

    @Test
    public void oneEuroEqualsOnePointTwelveDollars() {
        CurrencyQuantity oneEuro = new CurrencyQuantity(1f, Currency.EURO);
        CurrencyQuantity onePointTwelveDollars = new CurrencyQuantity(1.12f, Currency.DOLLAR);
        assertEquals(oneEuro, onePointTwelveDollars);
    }
}
