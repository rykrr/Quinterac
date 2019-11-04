package ca.queensu.cisc327.afk.transactions.script;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.Console;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.Transaction;

import java.util.Map;

public class Placeholder extends TransactionScript<Transaction> {

    @Override
    public Transaction execute(Console console, SessionType type, Map<String, Account> accounts) {
        return null;
    }
}
