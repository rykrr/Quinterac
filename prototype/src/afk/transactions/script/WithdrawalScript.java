package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.SessionType;
import afk.transactions.Deposit;
import afk.transactions.Withdrawal;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

import java.util.Map;

public class WithdrawalScript extends TransactionScript<Withdrawal> {
    @Override
    public Withdrawal execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {

        Account account = getAccount("Withdraw from", console, accounts);

        int amount;
        while(true) {
            amount = getAmount("Amount", console);
            try {
                return new Withdrawal(type, account, amount);
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
