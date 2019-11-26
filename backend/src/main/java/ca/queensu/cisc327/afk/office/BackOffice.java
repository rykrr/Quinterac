package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackOffice {

    private static final NullAccount nullAccount = new NullAccount();

    // Class input parameters //////////////////////
    ///////////////////////////////////////////////
    private List<Account>           original;
    private List<Transaction>       transactions;

    // Account Table ///////////////////////////////
    ///////////////////////////////////////////////
    private Map<String, Account>    accounts     = new HashMap<>();


    public BackOffice(List<Account> accountsList, List<Transaction> transactions) {

        // Input parameters to be used in execute()
        this.original = accountsList;
        this.transactions = transactions;

        // Maps all the account numbers in the master accounts
        // list to their respective accounts. Also checks for
        // accounts that share the same account number.
        for(Account account : accountsList) {
            if(accounts.containsKey(account.getNumber()))
                throw new IllegalArgumentException("Duplicate account numbers are forbidden");
            accounts.put(account.getNumber(), account.clone());
        }

        // The NullAccount is inserted into the AccountTable.
        // This ensures the unused fields in transactions don't
        // appear as non-existent accounts. In the future, the
        // Action.assertExists() should handle this as an edge case.
        accounts.put(nullAccount.getNumber(), nullAccount);
    }

    public List<Account> execute() {

        // This is only here for error tracking purposes
        int transactionIndex = 0;

        try {
            for (Transaction transaction : transactions) {
                // This uses the Action interface given by the
                // TransactionType enum to abstract away the
                // individual transaction implementation details.
                // The alternative might be a switch (a code smell)
                transaction.getType().getAction().execute(accounts, transaction);
                transactionIndex++;
            }
        }
        catch(ActionFailedException e) {

            // Something bad happened //

            // Print all the information surrounding the error,
            // including the account table at time of failure;
            System.err.println("Back office detected a failure on transaction "+ transactionIndex);
            System.err.println(transactions.get(transactionIndex).toString());
            e.printStackTrace();

            System.err.println("\n\nMaster account table at time of failure:");
            for(Account account : accounts.values())
                System.err.println("\t" + account);

            // On failure, return the original master account list
            // because we can no longer trust the integrity of the
            // data in the account table containing the modifications.
            return original;
        }

        // All transactions ran successfully //

        // Remove the hack-y nullAccount from the account table.
        // We don't want it to appear in the master account file.
        accounts.remove(nullAccount.getNumber());

        // Convert the table back into a list. Once sorted, this
        // should become the master accounts list and can be
        // written to a file.
        ArrayList<Account> output = new ArrayList<>(accounts.values());
        output.sort(null);
        return output;
    }

}
