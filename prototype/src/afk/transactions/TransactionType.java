package afk.transactions;

import afk.transactions.script.*;

public enum TransactionType {
    DEPOSIT         ("DEP", "deposit",      new DepositScript()),
    WITHDRAW        ("WDR", "withdraw",     new WithdrawalScript()),
    TRANSFER        ("XFR", "transfer",     new TransferScript()),
    CREATE_ACCOUNT  ("NEW", "createacct",   new CreateAcctScript()),
    DELETE_ACCOUNT  ("DEL", "deleteacct",   new DeleteAcctScript()),
    END_OF_SESSION  ("EOS", "logout",       new LogoutScript());

    private String shortCode;
    private String command;
    private TransactionScript script;

    TransactionType(String shortCode, String command, TransactionScript script) {
        this.shortCode = shortCode;
        this.command = command;
        this.script = script;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCommand() {
        return command;
    }

    public TransactionScript getScript() { return script; }

    public static TransactionType stringToEnum(String command) {
        for(TransactionType t : values())
            if(t.getCommand().equals(command))
                return t;

        // TODO: Maybe throw an exception instead
        return null;
    }

}
