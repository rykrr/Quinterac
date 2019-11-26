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

public class CreateAccountTest {

    private static final String ACCOUNT_NUMBER = "1000000";

    private Map<String, Account> accounts;

    @Before
    public void setup() {
        accounts = new HashMap<>();
        accounts.put("0000000", new NullAccount());
    }


    public boolean newAccountExists() {
        return accounts.containsKey(ACCOUNT_NUMBER);
    }


    public void createAccount(String account)
            throws ActionFailedException, RuntimeException {

        Transaction transaction = new Transaction("NEW", account, "0000000", "0", "");

        Action action = new CreateAccountAction();
        action.execute(accounts, transaction);
    }


     //////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

    @Test
    public void createAccountTest() throws ActionFailedException {
        assert !newAccountExists();
        createAccount(ACCOUNT_NUMBER);
        assert newAccountExists();
    }

    @Test(expected = ActionFailedException.class)
    public void doubleCreate() throws ActionFailedException {
        assert !newAccountExists();
        createAccount(ACCOUNT_NUMBER);
        assert newAccountExists();

        try {
            createAccount(ACCOUNT_NUMBER);
        }
        catch(ActionFailedException exception) {
            assert newAccountExists();
            throw exception;
        }
    }


    @Test(expected = ActionFailedException.class)
    public void createNull() throws ActionFailedException {
        assert !newAccountExists();

        try {
            createAccount("0000000");
        }
        catch(ActionFailedException exception) {
            assert !newAccountExists();
            throw exception;
        }
    }
}
