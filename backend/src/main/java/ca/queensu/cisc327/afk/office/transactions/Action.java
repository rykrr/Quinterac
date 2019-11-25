package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;

import java.util.Map;

public interface Action {
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException;


    static void assertAccountsExist(Map<String, Account> accounts, Transaction t)
            throws ActionFailedException {

        if(!accounts.containsKey(t.getSourceNumber()))
            throw new ActionFailedException("Source account does not exist in master accounts record.");

        if(!accounts.containsKey(t.getDestinationNumber()))
            throw new ActionFailedException("Destination account does not exist in master accounts record.");
    }


    static void assertAccountsExist(Map<String, Account> accounts, Transaction t, String message)
            throws ActionFailedException {

        try {
            assertAccountsExist(accounts, t);
        }
        catch(ActionFailedException e) {
            throw new ActionFailedException(message);
        }
    }

    static void apply(Map<String, Account> accounts, Transaction transaction, String number)
            throws ActionFailedException {

        if(!accounts.containsKey(number))
            throw new ActionFailedException("Account " + number + " does not exist");

        try {
            accounts.get(number).applyTransaction(transaction);
        }
        catch(Exception e) {
            throw new ActionFailedException("An unknown error occurred.", e);
        }
    }
}
