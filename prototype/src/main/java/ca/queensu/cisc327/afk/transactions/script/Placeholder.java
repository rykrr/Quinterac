package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.SessionType;
import afk.transactions.Transaction;

import java.util.Map;

public class Placeholder extends TransactionScript<Transaction> {

    @Override
    public Transaction execute(Console console, SessionType type, Map<String, Account> accounts) {
        return null;
    }
}
