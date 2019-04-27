import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    @Test
    public void canCreateBankAccount() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(0f, jez);
    }

    @Test
    public void canDeposit() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(0f, jez);
        assertTrue(a.deposit(10f));
        assertEquals(10f, a.getBalance(pin), 0f);
    }

    @Test
    public void canWithdraw() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(100f, jez);
        assertTrue(a.withdraw(10f, pin));
        assertEquals(90f, a.getBalance(pin), 0f);
    }

    @Test
    public void cannotOverdraw() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(100f, jez);
        assertFalse(a.withdraw(101f, pin));
        assertEquals(100f, a.getBalance(pin), 0f);
    }

    @Test
    public void canTransfer() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(100f, jez);
        BankAccount b = new BankAccount(100f, jez);
        assertTrue(a.transfer(b, 50f, pin));
        assertEquals(50f, a.getBalance(pin), 0f);
        assertEquals(150f, b.getBalance(pin), 0f);
    }

    @Test
    public void cannotTransferMoreThanBalance() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(100f, jez);
        BankAccount b = new BankAccount(100f, jez);
        assertFalse(a.transfer(b, 101f, pin));
        assertEquals(100f, a.getBalance(pin), 0f);
        assertEquals(100f, b.getBalance(pin), 0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDepositNegativeAmount() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(-100f, jez);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotWithdrawNegativeAmount() {
        int pin = 1234;
        User jez = new User("jez", pin);
        BankAccount a = new BankAccount(0f, jez);
        a.withdraw(-100f, pin);
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
