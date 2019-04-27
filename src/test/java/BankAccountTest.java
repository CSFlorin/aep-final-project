import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
}
