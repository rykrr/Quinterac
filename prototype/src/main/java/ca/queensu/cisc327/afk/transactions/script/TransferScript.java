package ca.queensu.cisc327.afk.transactions.script;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.Console;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.Transfer;
import ca.queensu.cisc327.afk.transactions.Withdrawal;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.DailyLimitViolation;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.TransactionLimitViolation;

import java.util.Map;

public class TransferScript extends TransactionScript<Transfer> {
    @Override
    public Transfer execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {

        if(accounts.size() == 1)
            throw new TransactionCancelledException("Error: There are no accounts to transfer to");

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
