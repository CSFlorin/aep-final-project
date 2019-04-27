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
//
//    @Test
//    public void jezCanTransferToAnotherOfHisAccounts() {
//        BankAccount a = new BankAccount(100f, jez);
//        BankAccount b = new BankAccount(100f, jez);
//        assertTrue(a.transfer(b, 50f, jezPin));
//        assertEquals(50f, a.getBalance(jezPin), 0f);
//        assertEquals(150f, b.getBalance(jezPin), 0f);
//    }
//
//    @Test
//    public void cannotTransferMoreThanBalance() {
//        BankAccount a = new BankAccount(100f, jez);
//        BankAccount b = new BankAccount(100f, jez);
//        assertFalse(a.transfer(b, 101f, jezPin));
//        assertEquals(100f, a.getBalance(jezPin), 0f);
//        assertEquals(100f, b.getBalance(jezPin), 0f);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void cannotDepositNegativeAmount() {
//        BankAccount a = new BankAccount(-100f, jez);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void cannotWithdrawNegativeAmount() {
//        BankAccount a = new BankAccount(0f, jez);
//        a.withdraw(-100f, jezPin);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void cannotGetBalanceWithInvalidPin() {
//        BankAccount a = new BankAccount(100f, jez);
//        a.getBalance(1235);
//    }
//
//    @Test
//    public void jezCannotWithdrawFromFlorinsAccount() {
//        BankAccount a = new BankAccount(100f, jez);
//        BankAccount b = new BankAccount(100f, florin);
//        try {
//            b.withdraw(10f, jezPin);
//            fail("Jez allowed to withdraw");
//        } catch (RuntimeException e) {
//            assertEquals(100f, b.getBalance(florinPin), 0f);
//            assertEquals(100f, a.getBalance(jezPin), 0f);
//        }
//    }

//    @Test
//    public void canMergeAccounts() {
//        BankAccount a = new BankAccount(100);
//        BankAccount b = new BankAccount(100);
//        assertTrue(a.merge(b));
//        assertEquals(100, a.getBalance());
//        assertEquals(100, b.getBalance());
//    }
}
