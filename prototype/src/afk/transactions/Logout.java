package afk.transactions;

import afk.Account;
import afk.NullAccount;

public class Logout extends Transaction {
    public Logout() {
        super(TransactionType.END_OF_SESSION, new NullAccount(), new NullAccount(), 0);
    }
}
