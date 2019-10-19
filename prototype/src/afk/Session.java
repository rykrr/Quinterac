package afk;

import afk.transactions.Transaction;
import afk.transactions.TransactionType;
import afk.transactions.script.TransactionCancelledException;
import afk.transactions.script.TransactionScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

    private SessionType sessionType;

    // This is built from the valid account list
    private Map<String, Account> accounts = new HashMap<>();

    public Session(SessionType sessionType, List<Account> validAccounts) {
        this.sessionType = sessionType;

        for(Account account : validAccounts)
            accounts.put(account.getNumber(), account);
    }

    public List<Transaction> run(Console console) {
        List<Transaction> transactions = new ArrayList<>();

        TransactionType command;
        TransactionScript<?> script;

        do {
            System.out.print(sessionType.getName() + "> ");
            command = TransactionType.stringToEnum(console.readString());

            if(command == null) {
                System.out.println("Error: Command not found");
                continue;
            }

            try {
                script = command.getScript();
            }
            catch(TransactionCancelledException cancel) {
                System.out.println("Transaction cancelled");
                continue;
            }

            transactions.add(script.execute(console, sessionType, accounts));

        } while(command != TransactionType.END_OF_SESSION);

        return transactions;
    }
}
