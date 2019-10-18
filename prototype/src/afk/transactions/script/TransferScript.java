package afk.transactions.script;

import afk.Account;
import afk.Console;
import afk.SessionType;
import afk.transactions.Transfer;
import afk.transactions.Withdrawal;
import afk.transactions.constraints.exceptions.DailyLimitViolation;
import afk.transactions.constraints.exceptions.TransactionLimitViolation;

import java.util.Map;

public class TransferScript extends TransactionScript<Transfer> {
    @Override
    public Transfer execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {

        Account src = getAccount("Transfer from", console, accounts);
        Account dst = getAccount("Transfer to  ", console, accounts);

        while(src.equals(dst)) {
            System.out.println("Source and destination accounts may not be the same");
            System.out.println("Please specify another destination account");
            dst = getAccount("Transfer to  ", console, accounts);
        }

        int amount;
        while(true) {
            amount = getAmount("Amount ", console);
            try {
                return new Transfer(type, src, dst, amount);
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
