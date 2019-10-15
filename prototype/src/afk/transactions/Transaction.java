package afk.transactions;

import afk.Account;

public abstract class Transaction {

    private TransactionType type;
    private Account source;
    private Account destination;
    private int amount;


    public Transaction(TransactionType type, Account source, Account destination, int amount) {
        this.type        = type;
        this.source      = source;
        this.destination = destination;
        this.amount      = amount;
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
}
