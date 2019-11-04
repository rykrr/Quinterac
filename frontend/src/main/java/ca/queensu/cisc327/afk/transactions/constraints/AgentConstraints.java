package ca.queensu.cisc327.afk.transactions.constraints;

import ca.queensu.cisc327.afk.transactions.TransactionType;

public class AgentConstraints extends TransactionConstraints {

    public AgentConstraints() {
        addAllowedTransactionType(TransactionType.DEPOSIT);
        addAllowedTransactionType(TransactionType.WITHDRAW);
        addAllowedTransactionType(TransactionType.TRANSFER);
        addAllowedTransactionType(TransactionType.CREATE_ACCOUNT);
        addAllowedTransactionType(TransactionType.DELETE_ACCOUNT);
        addAllowedTransactionType(TransactionType.END_OF_SESSION);

        setPerTransactionLimit(TransactionType.DEPOSIT,  99999999);
        setPerTransactionLimit(TransactionType.WITHDRAW, 99999999);
        setPerTransactionLimit(TransactionType.TRANSFER, 99999999);
    }

}
