package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.Map;

public class Deposit implements Action {

    @Override
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException {

        Action.assertAccountsExist(accounts, transaction);
        Action.apply(accounts, transaction, transaction.getDestinationNumber());
    }
}
