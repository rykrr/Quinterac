package afk.transactions.constraints.exceptions;

public class TransactionLimitViolation extends ConstraintException {

    public TransactionLimitViolation(String message) {
        super(message);
    }
}
