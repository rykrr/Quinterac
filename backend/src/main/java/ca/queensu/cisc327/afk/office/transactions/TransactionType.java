package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.transactions.actions.*;

public enum TransactionType {
    DEPOSIT         ("DEP", new DepositAction()         ),
    WITHDRAW        ("WDR", new WithdrawalAction()      ),
    TRANSFER        ("XFR", new TransferAction()        ),
    CREATE_ACCOUNT  ("NEW", new CreateAccountAction()   ),
    DELETE_ACCOUNT  ("DEL", new DeleteAccountAction()   ),
    END_OF_SESSION  ("EOS", (a,t) -> {}                 );

    private String shortCode;
    private Action action;

    TransactionType(String shortCode, Action action) {
        this.shortCode = shortCode;
        this.action = action;
    }

    public String getShortCode() {
        return shortCode;
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

}
