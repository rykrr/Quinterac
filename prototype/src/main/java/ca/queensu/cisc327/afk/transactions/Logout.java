package ca.queensu.cisc327.afk.transactions;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.NullAccount;

public class Logout extends Transaction {
    public Logout() {
        super(TransactionType.END_OF_SESSION, new NullAccount(), new NullAccount(), 0);
    }

    @Override
    public String getSuccessMessage() {
        return "Successfully logged out";
    }
}
