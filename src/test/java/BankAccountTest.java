import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    int jezPin = 1234;
    User jez = new User("jez", jezPin);

    @Test
    public void canCreateBankAccount() {
        BankAccount a = new BankAccount(0f, jez);
    }

    @Test
    public void canDeposit() {
        BankAccount a = new BankAccount(0f, jez);
        assertTrue(a.deposit(10f));
        assertEquals(10f, a.getBalance(jezPin), 0f);
    }

    @Test
    public void canWithdraw() {
        BankAccount a = new BankAccount(100f, jez);
        assertTrue(a.withdraw(10f, jezPin));
        assertEquals(90f, a.getBalance(jezPin), 0f);
    }

    @Test
    public void cannotOverdraw() {
        BankAccount a = new BankAccount(100f, jez);
        assertFalse(a.withdraw(101f, jezPin));
        assertEquals(100f, a.getBalance(jezPin), 0f);
    }

    @Test
    public void canTransfer() {
        BankAccount a = new BankAccount(100f, jez);
        BankAccount b = new BankAccount(100f, jez);
        assertTrue(a.transfer(b, 50f, jezPin));
        assertEquals(50f, a.getBalance(jezPin), 0f);
        assertEquals(150f, b.getBalance(jezPin), 0f);
    }

    @Test
    public void cannotTransferMoreThanBalance() {
        BankAccount a = new BankAccount(100f, jez);
        BankAccount b = new BankAccount(100f, jez);
        assertFalse(a.transfer(b, 101f, jezPin));
        assertEquals(100f, a.getBalance(jezPin), 0f);
        assertEquals(100f, b.getBalance(jezPin), 0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDepositNegativeAmount() {
        BankAccount a = new BankAccount(-100f, jez);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotWithdrawNegativeAmount() {
        BankAccount a = new BankAccount(0f, jez);
        a.withdraw(-100f, jezPin);
    }

    @Test(expected = RuntimeException.class)
    public void cannotGetBalanceWithInvalidPin() {
        BankAccount a = new BankAccount(100f, jez);
        a.getBalance(1235);
    }

//    @Test
//    public void canMergeAccounts() {
//        BankAccount a = new BankAccount(100);
//        BankAccount b = new BankAccount(100);
//        assertTrue(a.merge(b));
//        assertEquals(100, a.getBalance());
//        assertEquals(100, b.getBalance());
//    }
}
