package ca.queensu.cisc327.afk.transactions.script;
import ca.queensu.cisc327.afk.*;
import ca.queensu.cisc327.afk.transactions.CreateAcct;
import ca.queensu.cisc327.afk.transactions.TransactionType;

import java.util.Map;

public class CreateAcctScript extends TransactionScript<CreateAcct> {

    private static final TransactionType TYPE = TransactionType.CREATE_ACCOUNT;

    public CreateAcct execute(Console console, SessionType sessionType, Map<String, Account> accounts) {

        String inputAcc;

        System.out.println("Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)\n");
        inputAcc = console.readAccount();


        while (inputAcc.charAt(0) == '0' || accounts.containsKey(inputAcc)) {
            // account number cannot start with 0,
            if(inputAcc.charAt(0) == '0'){
                System.out.println("Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)\n");
            }
            // to check if the account number is existing.
            else if(accounts.containsKey(inputAcc)){
                System.out.println("The account number already exist!\n");
            }
            System.out.println("Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)\n");
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

        Account newAccount = new Account(inputAcc);
        accounts.put(inputAcc, newAccount);
        return new CreateAcct(newAccount, inputName);
        }

}
