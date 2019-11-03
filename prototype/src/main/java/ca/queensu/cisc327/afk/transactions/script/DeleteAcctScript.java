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
        while (!accounts.containsKey(inputAcc)) {
            System.out.println("The account does not exist, please enter valid account number.");
            inputAcc = console.readAccount();
        }
		
        String inputName = console.readString();
        System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters that do not beginning with 0\n");

        // the length of account name must be 3 to 30 and cannot begin with 0
        while (inputName.length() < 3 || inputName.length() > 30 || inputName.charAt(0) == '0'){
            if(inputName.length() < 3 || inputName.length() > 30){
                System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters\n");
                inputName = console.readString();
            }
			/*
            else if(inputName.charAt(0) == '0'){
                System.out.println("Please enter an account name that does not beginning with 0\n");
                inputName = console.readString();
            }
			*/
        }

        Account deleteAccount = accounts.get(inputAcc);
        accounts.remove(inputAcc);
        return new DeleteAcct(deleteAccount, inputName);
    }


}
