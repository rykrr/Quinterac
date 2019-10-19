package afk.transactions;

import afk.Account;
import afk.SessionType;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class Transfer extends Transaction {

    private static final TransactionType TYPE = TransactionType.TRANSFER;

    public Transfer(SessionType session, Account source, Account destination, int amount) {
        super(TYPE, source, destination, amount);

        // TODO Better exception messages
        int limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount violates transfer limit of " + limit);

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < source.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount violates daily limit of "
                    + limit + " for transferring out of account");

        source.addTransaction(this);
    }

    public String getSuccessMessage() {
        return "Successfully transferred $" + ((double) getAmount()/100)
                + " from account #" + getSourceAccount().getNumber()
                + " to account #" + getDestinationAccount().getNumber();
    }
}
