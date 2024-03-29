package ca.queensu.cisc327.afk.transactions;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.ConstraintException;

public abstract class Transaction {

    private TransactionType type;
    private Account source;
    private Account destination;
    private int amount;
    private String name;


    public Transaction(TransactionType type, Account source, Account destination, int amount, String name) {
        this.type        = type;
        this.source      = source;
        this.destination = destination;
        this.amount      = amount;
        this.name        = name;

        if(type == null || source == null || destination == null || name == null)
            throw new NullPointerException("No transaction field may be null!");

        if(source.isNewAccount() || destination.isNewAccount())
            throw new ConstraintException("Transactions must not involve newly created accounts.");
    }

    public Transaction(TransactionType type, Account source, Account destination, int amount) {
        this(type, source, destination, amount, "***");
    }

    public TransactionType getType() {
        return type;
    }

    public Account getSourceAccount() {
        return source;
    }

    public Account getDestinationAccount() {
        return destination;
    }

    public int getAmount() {
        return amount;
    }

    public abstract String getSuccessMessage();

    public String toString() {
        return type.getShortCode() + ' '
                + source.getNumber() + ' '
                + (amount == 0? "000" : amount) + ' '
                + destination.getNumber() + ' '
                + name;
    }
}
