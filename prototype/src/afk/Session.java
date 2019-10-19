package afk;

import afk.transactions.Transaction;
import afk.transactions.TransactionType;
import afk.transactions.constraints.TransactionConstraints;
import afk.transactions.script.TransactionCancelledException;
import afk.transactions.script.TransactionScript;

import java.util.*;

public class Session {

    private SessionType sessionType;
    private TransactionConstraints sessionConstraints;

    // This is built from the valid account list
    private Map<String, Account> accounts = new HashMap<>();

    public Session(SessionType sessionType, List<Account> validAccounts) {
        this.sessionType = sessionType;
        this.sessionConstraints = sessionType.getConstraints();

        for(Account account : validAccounts)
            accounts.put(account.getNumber(), account);

        System.out.println("Welcome! You have successfully logged in as " + sessionType.getName());
        System.out.print("Available commands: ");

        TransactionType[] commands = sessionConstraints.getAllowedTransactionTypes().toArray(new TransactionType[0]);
        for(int i = 0; i < commands.length; i++) {
            System.out.print(commands[i].getCommand());

            if(i < commands.length-1)
                System.out.print(", ");
        }
        System.out.println();
    }

    public List<Transaction> run(Console console) {
        List<Transaction> transactions = new ArrayList<>();

        Transaction transaction;
        TransactionType command;
        TransactionScript<?> script;

        do {
            System.out.print(sessionType.getName() + "> ");
            command = TransactionType.stringToEnum(console.readString());

            if(!sessionConstraints.isAllowedTransaction(command)) {
                System.out.println(sessionType.getName()
                        + " does not have permission to run \""  + command.getCommand() + "\"");
                continue;
            }

            if(command == null) {
                System.out.println("Error: Command not found");
                continue;
            }

            try {
                script = command.getScript();
            }
            catch(TransactionCancelledException cancel) {
                System.out.println(cancel.getMessage());
                continue;
            }

            transaction = script.execute(console, sessionType, accounts);
            transactions.add(transaction);
            System.out.println(transaction.getSuccessMessage());

        } while(command != TransactionType.END_OF_SESSION);

        return transactions;
    }
}
