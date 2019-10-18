package afk.transactions;

import java.util.ArrayList;
import afk.Console;
import afk.Account;
import afk.NullAccount;
import afk.SessionType;
import afk.Session;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class DeleteAcct extends Transaction {

    private static final TransactionType TYPE = TransactionType.DELETE_ACCOUNT;

    public DeleteAcct(SessionType session, Account source, int amount) {
        super(TYPE, source, new NullAccount(), amount);
        // initialize the console
        Console console = new Console();
        // read console for delete account
        ArrayList<String> list=new ArrayList<String>();
        if(console.readAccount().equals("Delete Account")) {
            int delete = 0;
            int stop = 0;
            // ask user to enter the account number and account name, enter 000000 to cancel the operation.
            while(delete == 0 || stop == 0){
                System.out.println("Please enter the account number to be deleted (enter ‘0000000’ to cancel)\n");
                String inputAcc = console.readAccount();
                if(inputAcc.equals("0000000")){
                    stop = 1;
                }

                System.out.println("Please enter the account name to be deleted (enter ‘0000000’ to cancel)\n");
                String inputName = console.readString();
                if(inputName.equals("0000000")){
                    stop = 1;
                }

                // check if the account name and the account number is exist.
                for (int i = 0; i < validAccounts.length; i++) {
                    if(inputAcc == validAccounts[i].account){
                        for(int j = 0; j < validAccounts.length; j++){
                            if(inputName == validAccounts[i].name){
                                delete = 1;
                              list.add("DEL " + inputAcc + " 000 " +inputName);
                                System.out.println("Success!\n");
                            }
                        }

                    }
                }

                if(delete == 0){
                    System.out.println("Please enter the valid account number to be deleted (enter ‘0000000’ to cancel)\n");
                }
                if(inputAcc.equals("0000000")){
                    stop = 1;
                }
            }

        };

        // TODO Better exception messages
        int limit = session.getConstraints().getPerTransactionLimit(TYPE);
        if (limit != 0 && limit < amount)
            throw new TransactionLimitViolation("Amount violates withdrawal limit");

        limit = session.getConstraints().getDailyLimit(TYPE);
        if (limit != 0 && limit < source.getTransactionAmount(TYPE) + amount)
            throw new DailyLimitViolation("Amount violates daily limit for withdrawing from account");
    }

}