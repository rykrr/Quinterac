package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.Account;

public class Transaction {

    private TransactionType type;

    private String source;
    private String destination;
    private int    amount;
    private String name;


    public Transaction(String code, String source, String destination, String amount, String name) {
        this(TransactionType.codeToEnum(code), source, destination, Integer.parseInt(amount), name);
    }


    public Transaction(TransactionType type, String source, String destination, int amount, String name) {
        this.type        = type;
        this.source      = source;
        this.destination = destination;
        this.amount      = amount;
        this.name        = name;

        if(type == null || source == null || destination == null || name == null)
            throw new NullPointerException("No transaction field may be null!");
    }

    public TransactionType getType() {
        return type;
    }

    public String getSourceNumber() {
        return source;
    }

    public String getDestinationNumber() {
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
                + source + ' '
                + (amount == 0? "000" : amount) + ' '
                + destination + ' '
                + name;
    }
}
