package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.Transaction;
import ca.queensu.cisc327.afk.office.transactions.TransactionBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackOffice {

    private Map<String, Account>    accounts     = new HashMap<>();
    private List<Transaction>       transactions = new ArrayList<>();


    public BackOffice(List<Account> accountsList, List<TransactionBuilder> transactionBuilders) {
        for(Account account : accountsList)
            this.accounts.put(account.getNumber(), account);

        for(TransactionBuilder builder : transactionBuilders)
            this.transactions.add(builder.build(this.accounts));
    }

    public List<Account>  execute() {
        for(Transaction transaction : transactions) {

        }

        return null;
    }

}
