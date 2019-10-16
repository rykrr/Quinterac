package afk;

import afk.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {

    // This is built from the valid account list
    Map<String, Account> accounts = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();


    public Session(SessionType sessionType, List<Account> validAccounts) {
        // This is after a login command
    }

    /* We need to handle commands here somehow */

    public List<Transaction> logout() {
        return transactions;
    }
}
