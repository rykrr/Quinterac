package afk.transactions.constraints;

import afk.transactions.TransactionType;

public class AgentConstraints extends TransactionConstraints {

    public AgentConstraints() {
        addAllowedTransactionType(TransactionType.DEPOSIT);
        addAllowedTransactionType(TransactionType.WITHDRAW);
        addAllowedTransactionType(TransactionType.TRANSFER);
        addAllowedTransactionType(TransactionType.CREATE_ACCOUNT);
        addAllowedTransactionType(TransactionType.DELETE_ACCOUNT);

        // TODO Set actual limits
        setPerTransactionLimit(TransactionType.DEPOSIT,  99999999);
        setPerTransactionLimit(TransactionType.WITHDRAW, 99999999);
        setPerTransactionLimit(TransactionType.TRANSFER, 99999999);
    }

}
