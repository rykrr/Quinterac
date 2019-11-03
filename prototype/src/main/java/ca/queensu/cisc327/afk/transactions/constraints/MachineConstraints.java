package ca.queensu.cisc327.afk.transactions.constraints;

import ca.queensu.cisc327.afk.transactions.TransactionType;

public class MachineConstraints extends TransactionConstraints {

    public MachineConstraints() {
        addAllowedTransactionType(TransactionType.DEPOSIT);
        addAllowedTransactionType(TransactionType.WITHDRAW);
        addAllowedTransactionType(TransactionType.TRANSFER);
        addAllowedTransactionType(TransactionType.END_OF_SESSION);

        setPerTransactionLimit(TransactionType.DEPOSIT, 200000);
        setDailyLimit(TransactionType.DEPOSIT, 500000);

        setPerTransactionLimit(TransactionType.WITHDRAW, 200000);
        setDailyLimit(TransactionType.DEPOSIT, 500000);

        setPerTransactionLimit(TransactionType.TRANSFER, 1000000);
        setDailyLimit(TransactionType.TRANSFER, 1000000);
    }

}
