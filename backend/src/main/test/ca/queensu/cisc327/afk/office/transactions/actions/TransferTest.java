package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TransferTest {

    private static final String ACCOUNT_NUMBER_A = "1000000";
    private static final String ACCOUNT_NUMBER_B = "2000000";
    private static final String INVALID_NUMBER   = "9000000";

    private static final int STARTING_BALANCE = 10000;

    private Map<String, Account> accounts;
    private Account accountA, accountB;

    @Before
    public void setup() {
        accountA = new Account("", ACCOUNT_NUMBER_A, STARTING_BALANCE);
        accountB = new Account("", ACCOUNT_NUMBER_B, STARTING_BALANCE);

        accounts = new HashMap<>();
        accounts.put(accountA.getNumber(), accountA);
        accounts.put(accountB.getNumber(), accountB);
        accounts.put("0000000", new NullAccount());
    }


    public boolean balancesUnchanged() {
        return accountA.getBalance() == STARTING_BALANCE
                && accountB.getBalance() == STARTING_BALANCE;
    }


    public boolean balancesAdjusted(Account src, Account dst, int amount) {
        return src.getBalance() == STARTING_BALANCE - amount
                && dst.getBalance() == STARTING_BALANCE + amount;
    }


    public void transfer(String src, String dst, String amount)
            throws ActionFailedException, RuntimeException {

        Transaction transaction = new Transaction("XFR", src, dst, amount, "");

        Action action = new TransferAction();
        action.execute(accounts, transaction);
    }

    //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////


    @Test
    public void transferTest() throws ActionFailedException {
        assert balancesUnchanged();
        transfer(ACCOUNT_NUMBER_A, ACCOUNT_NUMBER_B, "10000");
        assert balancesAdjusted(accountA, accountB, 10000);
    }


    @Test
    public void transferNothing() throws ActionFailedException {
        assert balancesUnchanged();
        transfer(ACCOUNT_NUMBER_A, ACCOUNT_NUMBER_B, "00000");
        assert balancesUnchanged();
    }


    @Test(expected = ActionFailedException.class)
    public void insufficientFunds() throws ActionFailedException {
        assert balancesUnchanged();

        try {
            transfer(ACCOUNT_NUMBER_A, ACCOUNT_NUMBER_B, "99999");
        }
        catch(ActionFailedException exception) {
            assert balancesUnchanged();
            throw exception;
        }
    }


    @Test(expected = ActionFailedException.class)
    public void nonexistentSourceAccount() throws ActionFailedException {
        assert balancesUnchanged();

        try {
            transfer(INVALID_NUMBER, ACCOUNT_NUMBER_B, "10000");
        }
        catch(ActionFailedException exception) {
            assert balancesUnchanged();
            throw exception;
        }
    }


    @Test(expected = ActionFailedException.class)
    public void nonexistentDestinationAccount() throws ActionFailedException {
        assert balancesUnchanged();

        try {
            transfer(ACCOUNT_NUMBER_A, INVALID_NUMBER, "10000");
        }
        catch(ActionFailedException exception) {
            assert balancesUnchanged();
            throw exception;
        }
    }

}
