package ca.queensu.cisc327.afk.transactions.script;

import ca.queensu.cisc327.afk.Account;
import ca.queensu.cisc327.afk.Console;
import ca.queensu.cisc327.afk.SessionType;
import ca.queensu.cisc327.afk.transactions.Deposit;
import ca.queensu.cisc327.afk.transactions.Withdrawal;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.ConstraintException;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.DailyLimitViolation;
import ca.queensu.cisc327.afk.transactions.constraints.exceptions.TransactionLimitViolation;

import java.util.Map;

public class WithdrawalScript extends TransactionScript<Withdrawal> {
    @Override
    public Withdrawal execute(Console console, SessionType type, Map<String, Account> accounts) throws TransactionCancelledException {

        if(accounts.size() == 0)
            throw new TransactionCancelledException("Error: There are no accounts to withdraw from");

        Account account = getAccount("Withdraw from", console, accounts);

        int amount;
        while(true) {
            amount = getAmount("Amount", console);
            try {
                return new Withdrawal(type, account, amount);
            }
            catch(ConstraintException v) {
                System.out.println(v.getMessage());
            }
        }
    }
}
