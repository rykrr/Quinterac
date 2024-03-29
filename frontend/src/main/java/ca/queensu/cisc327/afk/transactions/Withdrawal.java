package ca.queensu.cisc327.afk.transactions;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.NullAccount;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.DailyLimitViolation;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class Withdrawal extends Transaction {

    private static final TransactionType TYPE = TransactionType.WITHDRAW;

    public Withdrawal(SessionType session, Account source, int amount) {
        super(TYPE, source, new NullAccount(), amount);

        // TODO Better exception messages
        int limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount exceeds withdrawal limit of ¢" + limit);

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < source.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount exceeds daily limit of ¢"
                    + limit + " for withdrawing from account");

        source.addTransaction(this);
    }

    public String getSuccessMessage() {
        return "Successfully withdrew $" + ((double) getAmount()/100)
                + " from account #" + getSourceAccount().getNumber();
    }
}
