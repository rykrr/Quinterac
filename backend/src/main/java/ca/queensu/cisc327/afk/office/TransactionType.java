package ca.queensu.cisc327.afk.office;

public enum TransactionType {
    DEPOSIT         ("DEP", "deposit"),
    WITHDRAW        ("WDR", "withdraw"),
    TRANSFER        ("XFR", "transfer"),
    CREATE_ACCOUNT  ("NEW", "createacct"),
    DELETE_ACCOUNT  ("DEL", "deleteacct"),
    END_OF_SESSION  ("EOS", "logout");

    private String shortCode;
    private String command;

    TransactionType(String shortCode, String command) {
        this.shortCode = shortCode;
        this.command = command;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCommand() {
        return command;
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
