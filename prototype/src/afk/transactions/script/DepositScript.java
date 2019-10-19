package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.Session;
import afk.SessionType;
import afk.transactions.Deposit;
import afk.transactions.TransactionType;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

import java.util.List;
import java.util.Map;

public class DepositScript extends TransactionScript<Deposit> {
    @Override
    public Deposit execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {

        if(accounts.size() == 0)
            throw new TransactionCancelledException("Error: There are no accounts to deposit to");

        Account account = getAccount("Deposit to ", console, accounts);

        int amount;
        while(true) {
            amount = getAmount("Amount", console);

            try {
                return new Deposit(type, account, amount);
            }
            catch(TransactionLimitViolation v) {
                System.out.println(v.getMessage());
            }
            catch(DailyLimitViolation v) {
                System.out.println(v.getMessage());
            }
        }
    }
}
