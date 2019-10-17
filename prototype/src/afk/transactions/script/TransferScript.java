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
    public Transfer execute(Console console, SessionType type, Map<String, Account> accounts) {

        Account src = getAccount("Transfer from", console, accounts);
        Account dst = getAccount("Transfer to  ", console, accounts);

        while(src.equals(dst)) {
            console.print("Source and destination accounts may not be the same");
            console.print("Please specify another destination account");
            dst = getAccount("Transfer to  ", console, accounts);
        }

        int amount;
        while(true) {
            amount = console.readAmount("Amount ");
            try {
                return new Transfer(type, src, dst, amount);
            }
            catch(TransactionLimitViolation v) {
                console.println(v.getMessage());
            }
            catch(DailyLimitViolation v) {
                console.println(v.getMessage());
            }
        }
    }
}
