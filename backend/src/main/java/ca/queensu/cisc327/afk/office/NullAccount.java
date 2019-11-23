package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.Transaction;

public class NullAccount extends Account {

    public NullAccount() {
        super("0000000", "0000000", 0);
    }

    @Override
    public void addTransaction(Transaction transaction) {
        throw new UnsupportedOperationException("NullAccount does not support transactions");
    }
}
