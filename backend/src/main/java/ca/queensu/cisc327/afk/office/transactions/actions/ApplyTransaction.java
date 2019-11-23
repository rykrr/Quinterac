package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.Map;

public class ApplyTransaction implements Action {

    @Override
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException {

        if(!Action.accountsExist(accounts, transaction))
            throw new ActionFailedException(
                    "Error. An account in the transaction does not exist in the master record.");

        try {
            // Apply the transaction to both accounts
            transaction.getSourceAccount().applyTransaction(transaction);
            transaction.getDestinationAccount().applyTransaction(transaction);
        }
        catch(Exception e) {
            throw new ActionFailedException("An unknown exception occurred", e);
        }
    }

}
