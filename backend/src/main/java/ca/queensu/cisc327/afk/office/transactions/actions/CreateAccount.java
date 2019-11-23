package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.Map;

public class CreateAccount implements Action {

    @Override
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException {

        Account account = transaction.getSourceAccount();

        if(Action.accountsExist(accounts, transaction))
            throw new ActionFailedException(
                    "Constraint failed. An account with the number "
                    + account.getNumber() + " already exists.");

        accounts.put(account.getNumber(), account);
    }
}
