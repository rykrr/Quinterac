package ca.queensu.cisc327.afk.office.transactions;

public class ActionFailedException extends Exception {

    public ActionFailedException(String what) {
        super(what);
    }

    public ActionFailedException(String what, Throwable t) {
        super(what, t);
    }
}
