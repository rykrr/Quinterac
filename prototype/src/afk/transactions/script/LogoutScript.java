package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.NullAccount;
import afk.SessionType;
import afk.transactions.Logout;
import afk.transactions.Transaction;
import afk.transactions.TransactionType;

import java.util.Map;

public class LogoutScript extends TransactionScript<Logout> {
    @Override
    public Logout execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {
        return new Logout();
    }
}
