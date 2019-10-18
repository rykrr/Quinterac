package afk;

import afk.transactions.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
	boolean finished;
	String[] summary;

    // This is built from the valid account list
    Map<String, Account> accounts = new HashMap<>();
    List<Transaction> transactions = new ArrayList<>();

    public Session(Console c, SessionType sessionType, List<Account> validAccounts) {
    }

    public List<Transaction> logout() {
        return transactions;
    }
}
