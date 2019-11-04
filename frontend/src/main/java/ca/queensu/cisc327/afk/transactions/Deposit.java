package ca.queensu.cisc327.afk.transactions;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.NullAccount;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.DailyLimitViolation;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class Deposit extends Transaction {

    private static final TransactionType TYPE = TransactionType.DEPOSIT;

    public Deposit(SessionType session, Account destination, int amount) {
        super(TYPE, new NullAccount(), destination, amount);

        // TODO Better exception messages
        int limit;

        limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount exceeds deposit limit of ¢" + limit);

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < destination.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount exceeds daily limit of ¢"
                    + limit + " for depositing into account " + destination);

        destination.addTransaction(this);
    }

    public String getSuccessMessage() {
        return "Successfully deposited $" + ((double) getAmount()/100)
                + " into account #" + getDestinationAccount().getNumber();
    }
}
