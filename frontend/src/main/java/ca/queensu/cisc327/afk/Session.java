package ca.queensu.cisc327.afk;

import ca.queensu.cisc327.afk.transactions.Transaction;
import ca.queensu.cisc327.afk.transactions.TransactionType;
import ca.queensu.cisc327.afk.transactions.constraints.TransactionConstraints;
import ca.queensu.cisc327.afk.transactions.script.TransactionCancelledException;
import ca.queensu.cisc327.afk.transactions.script.TransactionScript;

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

            if(command == null) {
                System.out.println("Error: Command not found");
                continue;
            }

            if(!sessionConstraints.isAllowedTransaction(command)) {
                System.out.println(sessionType.getName()
                        + " does not have permission to run \""  + command.getCommand() + "\"");
                continue;
            }

            try {
                script = command.getScript();
                transaction = script.execute(console, sessionType, accounts);
            }
            catch(TransactionCancelledException cancel) {
                System.out.println(cancel.getMessage());
                continue;
            }

            transactions.add(transaction);
            System.out.println(transaction.getSuccessMessage());

        } while(command != TransactionType.END_OF_SESSION);

        return transactions;
    }
}
