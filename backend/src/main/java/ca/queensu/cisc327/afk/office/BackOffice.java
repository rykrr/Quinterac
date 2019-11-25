package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.ActionFailedException;
import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackOffice {

    private Map<String, Account>    accounts     = new HashMap<>();
    private List<Account>           original     = new ArrayList<>();
    private List<Transaction>       transactions;

    private NullAccount nullAccount = new NullAccount();


    public BackOffice(List<Account> accountsList, List<Transaction> transactions) {
        for(Account account : accountsList) {
            accounts.put(account.getNumber(), account);
            original.add(account.clone());
        }

        this.transactions = transactions;

        accounts.put(nullAccount.getNumber(), nullAccount);
    }

    public List<Account> execute() {

        int transactionIndex = 0;

        try {
            for (Transaction transaction : transactions) {
                System.out.println("\n\nTransaction " + transactionIndex);
                System.out.println(transaction);

                transaction.getType().getAction().execute(accounts, transaction);
                for(String account : accounts.keySet())
                    System.out.print(account + ", ");
                System.out.println();
                for(Account account : accounts.values())
                    System.out.println("\t" + account);

                System.out.println("\n\n");
                transactionIndex++;
            }
        }
        catch(ActionFailedException e) {
            System.err.println("Back office detected a failure on transaction " + transactionIndex);
            e.printStackTrace();

            System.err.println("\n\nMaster accounts list at time of failure:");
            for(Account account : accounts.values())
                System.err.println("\t" + account);

            return original;
        }

        accounts.remove(nullAccount.getNumber());

        ArrayList<Account> output = new ArrayList<>(accounts.values());
        output.sort(null);

        return output;
    }

}
