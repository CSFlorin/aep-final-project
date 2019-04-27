import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    @Test
    public void canCreateBankAccount() {
        BankAccount a = new BankAccount(0f);
    }

    @Test
    public void canDeposit() {
        BankAccount a = new BankAccount(0f);
        assertTrue(a.deposit(10f));
        System.out.println(a.getBalance());
        assertEquals(10f, a.getBalance(), 0f);
    }

    @Test
    public void canWithdraw() {
        BankAccount a = new BankAccount(100f);
        assertTrue(a.withdraw(10f));
        assertEquals(90f, a.getBalance(), 0f);
    }

    @Test
    public void cannotOverdraw() {
        BankAccount a = new BankAccount(100f);
        assertFalse(a.withdraw(101f));
        assertEquals(100f, a.getBalance(), 0f);
    }

    @Test
    public void canTransfer() {
        BankAccount a = new BankAccount(100f);
        BankAccount b = new BankAccount(100f);
        assertTrue(a.transfer(b, 50f));
        assertEquals(50f, a.getBalance(), 0f);
        assertEquals(150f, b.getBalance(), 0f);
    }

    @Test
    public void cannotTransferMoreThanBalance() {
        BankAccount a = new BankAccount(100f);
        BankAccount b = new BankAccount(100f);
        assertFalse(a.transfer(b, 101f));
        assertEquals(100f, a.getBalance(), 0f);
        assertEquals(100f, b.getBalance(), 0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotDepositNegativeAmount() {
        BankAccount a = new BankAccount(-100f);
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
