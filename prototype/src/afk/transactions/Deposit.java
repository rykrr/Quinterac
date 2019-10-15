package afk.transactions;

import afk.Account;
import afk.NullAccount;
import afk.SessionType;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class Deposit extends Transaction {

    private static final TransactionType TYPE = TransactionType.DEPOSIT;

    public Deposit(SessionType session, Account destination, int amount) {
        super(TYPE, new NullAccount(), destination, amount);

        // TODO Better exception messages
        int limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount violates deposit limit");

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < destination.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount violates daily limit for depositing into account");
    }

}
