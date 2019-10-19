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
        String number = null;

        do {
            if(number != null)
                System.out.println("Error: Account does not exist");

            try {
                System.out.print(prompt + " #");
                number = console.readAccount();

                if(number.equals(nullAccount.getNumber()))
                    throw new TransactionCancelledException();

                if(number.startsWith("0"))
                    throw new NumberFormatException();
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Please enter a valid account number");
                number = null;
            }
        } while(!accounts.containsKey(number));

        return accounts.get(number);
    }


    protected static int getAmount(String prompt, Console console) throws TransactionCancelledException {
        int amount = 0;

        do {
            try {
                System.out.print(prompt + " ¢");
                amount = console.readAmount();

                if(amount == 0)
                    throw new TransactionCancelledException();
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Please enter a valid amount");
                amount = 0;
            }
        } while(amount == 0);

        return amount;
    }

}
