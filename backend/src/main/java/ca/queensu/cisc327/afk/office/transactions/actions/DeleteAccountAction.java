package ca.queensu.cisc327.afk.office.transactions.actions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;
import ca.queensu.cisc327.afk.office.transactions.Action;
import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.Map;

public class DeleteAccountAction implements Action {

    @Override
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException {

        Action.assertAccountsExist(accounts, transaction,
            "Account to be deleted does not exist in master record");

        Account account = accounts.get(transaction.getSourceNumber());

        if(account.equals(new NullAccount()))
            throw new ActionFailedException(
                    "Constraint failed. Null account must not be deleted.");

        if(account.getBalance() != 0)
            throw new ActionFailedException(
                    "Constraint failed. Accounts with a non-zero balance cannot be deleted.");

        if(!account.getName().equals(transaction.getName()))
            throw new ActionFailedException(
                    "Constraint failed. Account name in transaction did not match the actual account");

        accounts.remove(account.getNumber());
    }
}
