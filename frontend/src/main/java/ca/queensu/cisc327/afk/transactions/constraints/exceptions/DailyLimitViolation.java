package ca.queensu.cisc327.afk.transactions.constraints.exceptions;

public class DailyLimitViolation extends ConstraintException {

    public DailyLimitViolation(String message) {
        super(message);
    }
}
