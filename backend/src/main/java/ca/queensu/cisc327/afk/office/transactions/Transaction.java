package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.Account;

public class Transaction {

    private TransactionType type;

    private Account source;
    private Account destination;
    private int     amount;
    private String  name;


    public Transaction(String code, Account source, Account destination, int amount, String name) {
        this(TransactionType.codeToEnum(code), source, destination, amount, name);
    }


    public Transaction(TransactionType type, Account source, Account destination, int amount, String name) {
        this.type        = type;
        this.source      = source;
        this.destination = destination;
        this.amount      = amount;
        this.name        = name;

        if(type == null || source == null || destination == null || name == null)
            throw new NullPointerException("No transaction field may be null!");
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

    public String getName() {
        return name;
    }

    public String toString() {
        return type.getShortCode() + ' '
                + source.getNumber() + ' '
                + (amount == 0? "000" : amount) + ' '
                + destination.getNumber() + ' '
                + name;
    }
}
