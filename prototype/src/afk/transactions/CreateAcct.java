package afk.transactions;
import afk.Account;
import afk.NullAccount;

public class CreateAcct extends Transaction {

    private static final TransactionType TYPE = TransactionType.CREATE_ACCOUNT;

    public CreateAcct(Account account, String name) {
        super(TYPE, account, new NullAccount(), 0, name);
    }

    public String getSuccessMessage() {
        return "Successfully created account #" + getSourceAccount().getNumber();
    }
}