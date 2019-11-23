package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;

import java.util.Map;

public interface Action {
    public void execute(Map<String, Account> accounts, Transaction transaction)
            throws ActionFailedException;


    public static boolean accountsExist(Map<String, Account> accounts, Transaction t) {
        Account src = t.getSourceAccount();
        Account dst = t.getDestinationAccount();

        NullAccount NULL = new NullAccount();

        return (accounts.containsKey(src) || src.equals(NULL))
            && (accounts.containsKey(dst) || dst.equals(NULL));
    }
}
