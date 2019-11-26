package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.transactions.actions.*;

public enum TransactionType {
    DEPOSIT         ("DEP", new DepositAction()         ),
    WITHDRAW        ("WDR", new WithdrawalAction()      ),
    TRANSFER        ("XFR", new TransferAction()        ),
    CREATE_ACCOUNT  ("NEW", new CreateAccountAction()   ),
    DELETE_ACCOUNT  ("DEL", new DeleteAccountAction()   ),
    END_OF_SESSION  ("EOS", (a,t) -> {}                 );


      //////////////////////////////////////////////////////////////
     /// Enum Definition //////////////////////////////////////////
    //////////////////////////////////////////////////////////////


    // Transaction Short Code refers to the 3 character
    // code that appears at the beginning of each line
    // in the merged transaction file
    private String shortCode;

    // Action implementations modify the master account
    // table entries. Similar to TransactionScript<T>
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
