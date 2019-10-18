package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.NullAccount;
import afk.SessionType;
import afk.transactions.Transaction;

import java.util.List;
import java.util.Map;

public abstract class TransactionScript<T extends Transaction> {
    public abstract T execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException;

    public static final NullAccount nullAccount = new NullAccount();

    protected static Account getAccount(String prompt, Console console, Map<String, Account> accounts) throws TransactionCancelledException {
        System.out.print(prompt + " #");
        String number = null;

        do {
            if(number != null)
                System.out.println("Error: Account does not exist");

            try {
                number = console.readAccount(prompt);

                if(number.equals(nullAccount.getNumber()))
                    throw new TransactionCancelledException();
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Please enter a valid account number");
                number = null;
            }
        } while(!accounts.containsKey(number));

        return accounts.get(number);
    }


    protected static int getAmount(String prompt, Console console) throws TransactionCancelledException {
        System.out.print(prompt + " ¢");
        int amount = 0;

        do {
            try {
                amount = console.readAmount();

                if(amount == 0)
                    throw new TransactionCancelledException();
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Please enter a valid amount [0, 99999999]");
                amount = 0;
            }
        } while(amount == 0);

        return amount;
    }

}
