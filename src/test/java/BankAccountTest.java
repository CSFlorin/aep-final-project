import org.junit.Test;

import static org.junit.Assert.*;

public class BankAccountTest {

    @Test
    public void canCreateBankAccount() {
        BankAccount a = new BankAccount(0);
    }

    @Test
    public void canDeposit() {
        BankAccount a = new BankAccount(0);
        assertTrue(a.deposit(10));
        assertEquals(10, a.getBalance());
    }

    @Test
    public void canWithdraw() {
        BankAccount a = new BankAccount(100);
        assertTrue(a.withdraw(10));
        assertEquals(90, a.getBalance());
    }

    @Test
    public void cannotWithdraw() {
        BankAccount a = new BankAccount(100);
        assertFalse(a.withdraw(101));
        assertEquals(100, a.getBalance());
    }
}
