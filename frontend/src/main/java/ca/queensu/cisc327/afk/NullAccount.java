package ca.queensu.cisc327.afk;

import ca.queensu.cisc327.afk.transactions.Transaction;

public class NullAccount extends Account {

    public NullAccount() {
        super("0000000");
    }

    @Override
    public void addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("NullAccount does not support transactions");
    }
}
