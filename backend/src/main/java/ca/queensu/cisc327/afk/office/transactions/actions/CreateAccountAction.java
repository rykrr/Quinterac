package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.Map;

public class CreateAccountAction implements Action {

    @Override
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException {

        if(accounts.containsKey(transaction.getSourceNumber()))
            throw new ActionFailedException(
                "Constraint failed. An account with the number "
                + transaction.getSourceNumber() + " already exists.");

        Account account = new Account(transaction.getName(), transaction.getSourceNumber(), 0);
        accounts.put(account.getNumber(), account);
    }
}
