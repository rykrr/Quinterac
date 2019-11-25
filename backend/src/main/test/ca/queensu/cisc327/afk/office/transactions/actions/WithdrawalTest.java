package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;
import ca.queensu.cisc327.afk.office.transactions.TransactionType;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

public class WithdrawalTest {

    private static final String ACCOUNT_NUMBER = "1000000";
    private static final String INVALID_NUMBER = "9000000";

    private static final int STARTING_BALANCE = 10000;

    private Map<String, Account> accounts;
    private Account account;

    @Before
    public void setup() {
        account = new Account("", ACCOUNT_NUMBER, STARTING_BALANCE);

        accounts = new HashMap<>();
        accounts.put(account.getNumber(), account);
        accounts.put("0000000", new NullAccount());
    }


    public boolean balanceUnchanged() {
        return account.getBalance() == STARTING_BALANCE;
    }


    public boolean balanceAdjusted(int amount) {
        return account.getBalance() == STARTING_BALANCE + amount;
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
    public void withdrawalTest() throws ActionFailedException {
        assert balanceUnchanged();
        withdraw(ACCOUNT_NUMBER, "10000");
        assert balanceAdjusted(-10000);
    }


    @Test
    public void withdrawNothing() throws ActionFailedException {
        assert balanceUnchanged();
        withdraw(ACCOUNT_NUMBER, "00000");
        assert balanceUnchanged();
    }


    @Test(expected = ActionFailedException.class)
    public void insufficientFunds() throws ActionFailedException {
        assert balanceUnchanged();

        try {
            withdraw(ACCOUNT_NUMBER, "99999");
        }
        catch(ActionFailedException exception) {
            assert balanceUnchanged();
            throw exception;
        }
    }


    @Test(expected = ActionFailedException.class)
    public void nonexistentAccount() throws ActionFailedException {
        assert balanceUnchanged();

        try {
            withdraw(INVALID_NUMBER, "10000");
        }
        catch(ActionFailedException exception) {
            assert balanceUnchanged();
            throw exception;
        }
    }

}
