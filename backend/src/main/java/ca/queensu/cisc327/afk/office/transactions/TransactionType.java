package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.transactions.actions.ApplyTransaction;
import ca.queensu.cisc327.afk.office.transactions.actions.CreateAccount;
import ca.queensu.cisc327.afk.office.transactions.actions.DeleteAccount;

public enum TransactionType {
    DEPOSIT         ("DEP", "deposit",      new ApplyTransaction()  ),
    WITHDRAW        ("WDR", "withdraw",     new ApplyTransaction()  ),
    TRANSFER        ("XFR", "transfer",     new ApplyTransaction()  ),
    CREATE_ACCOUNT  ("NEW", "createacct",   new CreateAccount()     ),
    DELETE_ACCOUNT  ("DEL", "deleteacct",   new DeleteAccount()     ),
    END_OF_SESSION  ("EOS", "logout",       (a,t) -> {}             );

    private String shortCode;
    private String command;
    private Action action;

    TransactionType(String shortCode, String command, Action action) {
        this.shortCode = shortCode;
        this.command = command;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCommand() {
        return command;
    }

    public Action getAction() {
        return action;
    }


    public static TransactionType codeToEnum(String code) {
        for(TransactionType t : values())
            if(t.getShortCode().equals(code))
                return t;

        throw new IllegalArgumentException(code + " is not a valid transaction code");
    }

    public static TransactionType stringToEnum(String command) {
        for(TransactionType t : values())
            if(t.getCommand().equals(command))
                return t;

        // TODO: Maybe throw an exception instead
        return null;
    }

}
