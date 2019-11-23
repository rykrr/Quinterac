package ca.queensu.cisc327.afk.office.transactions;

import ca.queensu.cisc327.afk.office.Account;
import ca.queensu.cisc327.afk.office.NullAccount;

import java.util.Map;
import java.util.NoSuchElementException;

public class TransactionBuilder {

    private static final NullAccount NULL = new NullAccount();

    private String  type;
    private String  source;
    private String  destination;
    private int     amount;
    private String  name;

    public TransactionBuilder() {
        // Set defaults to match
        // end of session token
        type        = "EOS";
        source      = "0000000";
        destination = "0000000";
        amount      = 0;
        name        = "***";
    }

    public Transaction build(Map<String, Account> accounts) {

        Account src;
        Account dst;

        if(accounts.containsKey(source))
            src = accounts.get(source);
        else if(source.equals(NULL.getNumber()))
            src = new NullAccount();
        else
            src = new Account(name, source, 0);

        if(accounts.containsKey(destination))
            dst = accounts.get(destination);
        else if(destination.equals(NULL.getNumber()))
            dst = new NullAccount();
        else
            dst = new Account(name, destination, 0);

        return new Transaction (
            TransactionType.codeToEnum(type),
            src, dst, amount, name
        );
    }


    public void setType(String type) {
        // Input validations go here
        this.type = type;
    }

    public void setSource(String source) {
        // Input validations go here
        this.source = source;
    }

    public void setDestination(String destination) {
        // Input validations go here
        this.destination = destination;
    }

    public void setAmount(String amount) {
        // Input validations go here
        this.amount = Integer.parseInt(amount);
    }

    public void setName(String name) {
        // Input validations go here
        this.name = name;
    }

}
