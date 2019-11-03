package afk.transactions;
import afk.Account;
import afk.NullAccount;


public class DeleteAcct extends Transaction {

    private static final TransactionType TYPE = TransactionType.DELETE_ACCOUNT;

    public DeleteAcct(Account account, String name) {
        super(TYPE, account, new NullAccount(), 0, name);
    }

    public String getSuccessMessage() {
        return "Successfully delete account #" + getSourceAccount().getNumber();
    }

}