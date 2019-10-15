package afk.transactions.constraints;

import afk.transactions.TransactionType;

public class MachineConstraints extends TransactionConstraints {

    public MachineConstraints() {
        addAllowedTransactionType(TransactionType.DEPOSIT);
        addAllowedTransactionType(TransactionType.WITHDRAW);
        addAllowedTransactionType(TransactionType.TRANSFER);

        // TODO Set actual limits
        setPerTransactionLimit(TransactionType.DEPOSIT, 100000);
        setDailyLimit(TransactionType.DEPOSIT, 200000);

        setPerTransactionLimit(TransactionType.WITHDRAW, 100000);
        setDailyLimit(TransactionType.DEPOSIT, 200000);

        setPerTransactionLimit(TransactionType.TRANSFER, 100000);
        setDailyLimit(TransactionType.TRANSFER, 200000);
    }

}
