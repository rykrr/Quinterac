package ca.queensu.cisc327.afk.office;

public class AccountConstraintViolation extends IllegalStateException {

    public AccountConstraintViolation(String what) {
        super(what);
    }
}
