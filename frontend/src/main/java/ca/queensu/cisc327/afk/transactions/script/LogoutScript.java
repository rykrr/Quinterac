package ca.queensu.cisc327.afk.transactions.script;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.Console;
import ca.queensu.cisc327.afk.NullAccount;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.Logout;
import ca.queensu.cisc327.afk.transactions.Transaction;
import ca.queensu.cisc327.afk.transactions.TransactionType;

import java.util.Map;

public class LogoutScript extends TransactionScript<Logout> {
    @Override
    public Logout execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {
        return new Logout();
    }
}
