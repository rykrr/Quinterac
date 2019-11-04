package ca.queensu.cisc327.afk.transactions.script;
import ca.queensu.cisc327.afk.*;
import ca.queensu.cisc327.afk.transactions.DeleteAcct;
import ca.queensu.cisc327.afk.transactions.TransactionType;
import java.util.Map;

public class DeleteAcctScript extends TransactionScript<DeleteAcct> {

    private static final TransactionType TYPE = TransactionType.DELETE_ACCOUNT;

    public DeleteAcct execute(Console console, SessionType sessionType, Map<String, Account> accounts) {
        // read console for delete account
        System.out.println("Please enter a seven-digit account number (enter ‘0000000’ to cancel)\n");
        System.out.print("#");
        String inputAcc = console.readAccount();
        while (!accounts.containsKey(inputAcc)) {
            if(inputAcc.equals("0000000")) {
                throw new TransactionCancelledException();
            }
            System.out.println("The account does not exist, please enter valid account number.");
            System.out.print("#");
            inputAcc = console.readAccount();
        }

        System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters that do not beginning with 0\n");
        System.out.print(">");
        String inputName = console.readString();
        // the length of account name must be 3 to 30 and cannot begin with 0
        while (inputName.length() < 3 || inputName.length() > 30 || inputName.charAt(0) == '0'){
            if(inputName.length() < 3 || inputName.length() > 30){
                System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters\n");
                System.out.print(">");
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
