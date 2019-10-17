package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.SessionType;
import afk.transactions.Transaction;

import java.util.List;
import java.util.Map;

public abstract class TransactionScript<T extends Transaction> {
    public abstract T execute(Console console, SessionType type, Map<String, Account> accounts);

    protected static Account getAccount(String prompt, Console console, Map<String, Account> accounts) {
        String number = null;

        do {
            if(number != null)
                console.println("Error: Account does not exist");
            number = console.readAccount(prompt);
        } while(!accounts.containsKey(number));

        return accounts.get(number);
    }
}
