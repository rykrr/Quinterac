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

public class DeleteAccountTest {

    private static final String ACCOUNT_NAME   = "Test";

    private static final String ACCOUNT_NUMBER = "1000000";

    private static final int STARTING_BALANCE = 10000;

    private Map<String, Account> accounts;
    private Account account;

    @Before
    public void setup() {
        account = new Account(ACCOUNT_NAME, ACCOUNT_NUMBER, STARTING_BALANCE);

        accounts = new HashMap<>();
        accounts.put(account.getNumber(), account);
        accounts.put("0000000", new NullAccount());
    }


    public boolean accountExists() {
        return accounts.containsKey(ACCOUNT_NUMBER);
    }


    public void deleteAccount(String account, String name)
            throws ActionFailedException, RuntimeException {

        Transaction transaction = new Transaction("DEL", account, "0000000", "0", name);

        Action action = new DeleteAccountAction();
        action.execute(accounts, transaction);
    }


    public void withdraw(String account, String amount)
            throws ActionFailedException, RuntimeException {

        Transaction transaction = new Transaction("WDR", account, "0000000", amount, "");

        Action action = new WithdrawalAction();
        action.execute(accounts, transaction);
    }


     //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @Test
    public void deleteAccountTest() throws ActionFailedException {
        assert accountExists();
        withdraw(ACCOUNT_NUMBER, STARTING_BALANCE + "");
        assert accountExists();
        deleteAccount(ACCOUNT_NUMBER, ACCOUNT_NAME);
        assert !accountExists();
    }

    @Test(expected = ActionFailedException.class)
    public void invalidName() throws ActionFailedException {
        assert accountExists();
        withdraw(ACCOUNT_NUMBER, STARTING_BALANCE + "");
        assert accountExists();

        try {
            deleteAccount(ACCOUNT_NUMBER, ACCOUNT_NAME + ACCOUNT_NAME);
        }
        catch(ActionFailedException exception) {
            assert accountExists();
            throw exception;
        }
    }

    @Test(expected = ActionFailedException.class)
    public void doubleDelete() throws ActionFailedException {
        assert accountExists();
        withdraw(ACCOUNT_NUMBER, STARTING_BALANCE + "");
        assert accountExists();
        deleteAccount(ACCOUNT_NUMBER, ACCOUNT_NAME);
        assert !accountExists();

        try {
            deleteAccount(ACCOUNT_NUMBER, ACCOUNT_NAME);
        }
        catch(ActionFailedException exception) {
            assert !accountExists();
            throw exception;
        }
    }

    @Test(expected = ActionFailedException.class)
    public void nonZeroBalanceTest() throws ActionFailedException {
        assert accountExists();

        try {
            deleteAccount(ACCOUNT_NUMBER, ACCOUNT_NAME);
        }
        catch(ActionFailedException exception) {
            assert accountExists();
            throw exception;
        }
    }

    @Test(expected = ActionFailedException.class)
    public void deleteNull() throws ActionFailedException {
        deleteAccount("0000000", "NULL");
    }
}
