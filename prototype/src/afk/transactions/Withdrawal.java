package afk.transactions;

import afk.Account;
import afk.NullAccount;
import afk.SessionType;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class Withdrawal extends Transaction {

    private static final TransactionType TYPE = TransactionType.WITHDRAW;

    public Withdrawal(SessionType session, Account source, int amount) {
        super(TYPE, source, new NullAccount(), amount);

        // TODO Better exception messages
        int limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount violates withdrawal limit of " + limit);

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < source.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount violates daily limit of "
                    + limit + " for withdrawing from account");

        source.addTransaction(this);
    }

    public String getSuccessMessage() {
        return "Successfully withdrew $" + ((double) getAmount()/100)
                + " from account #" + getSourceAccount().getNumber();
    }
}
