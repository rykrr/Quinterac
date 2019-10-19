package afk.transactions.script;
import afk.*;
import afk.transactions.DeleteAcct;
import afk.transactions.TransactionType;
import java.util.Map;

public class DeleteAcctScript extends TransactionScript<DeleteAcct> {

    private static final TransactionType TYPE = TransactionType.DELETE_ACCOUNT;

    public DeleteAcct execute(Console console, SessionType sessionType, Map<String, Account> accounts) {
        // read console for delete account
        String inputAcc = console.readAccount();
        while (accounts.containsKey(inputAcc)) {
            System.out.println("The account does not exist, please enter valid account number.");
            inputAcc = console.readAccount();
        }

        Account deleteAccount = new Account(inputAcc);
        accounts.remove(inputAcc, deleteAccount);
        return new DeleteAcct(deleteAccount, inputAcc);

    }


}