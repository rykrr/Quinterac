package afk.transactions;

import afk.Console;
import afk.Account;
import afk.NullAccount;
import afk.SessionType;
import afk.Session;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

public class CreateAcct extends Transaction {

    private static final TransactionType TYPE = TransactionType.DELETE_ACCOUNT;

    public CreateAcct(SessionType session, Account source, Session transactions) {
        super(TYPE, source, new NullAccount(), amount);
        // initialize the console
        Console console = new Console();
        // read console for create account
        if(console.readString().equals("Create Account")) {
            int stop = 0;
            int exist = 0;
            while(stop == 0){
                // ask user to enter their new account number
                System.out.println("Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)\n");
                String inputAcc = console.readAccount();
                // Enter 00000000 to cancel the operation
                if(inputAcc.equals("0000000")){
                    stop = 1;
                }
                // account number cannot start with 0, and the length must be 7
                while(inputAcc.charAt(0) == '0' || inputAcc.length() != 7 || exist == 1){
                    System.out.println("Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)\n");
                    inputAcc = console.readAccount();
                    // to check if the account number is existing.
                    for (int i = 0; i < validAccounts.length; i++) {
                        if(inputAcc == validAccounts[i].account){
                            exist = 1;
                            System.out.println("The account number already exist!\n");
                        }
                    }
                    if(inputAcc.equals("0000000")){
                        stop = 1;
                    }
                }

                //TODO
                while(stop == 0){
                    // ask user to enter their new account name
                    System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters that do not beginning with 0\n");
                    String inputName = console.readString();
                    // the length of account name must be 3 to 30 and cannot begin with 0
                    while(inputName.length() < 3 || inputName.length() > 30 || inputName.charAt(0) == '0'){
                        System.out.println("Please enter an account name that length between 3 and 30 alphanumeric characters that do not beginning with 0\n");
                        inputName = console.readString();
                    }

                    // add the operation information to transaction summary
                    transactions.add("NEW "  + inputAcc + " 000 " +inputName);
                    list.add("NEW " + inputAcc + " 000 " +inputName);
                    System.out.println("Success!\n");
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