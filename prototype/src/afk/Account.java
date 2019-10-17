package afk;

import afk.transactions.Transaction;
import afk.transactions.TransactionType;

import java.util.Map;

public class Account implements Comparable<Account> {

    private String number;

    private Map<TransactionType, Integer> transactions;

    public Account(String number) {
        if(number.length() != 7)
            throw new NumberFormatException("Account numbers must be 7 digits in length");

        if(number.charAt(0) == '0' && !number.equals("0000000"))
            throw new NumberFormatException("Account numbers must not start with 0");

        for(int i = 0; i < 7; i++)
            if(!Character.isDigit(number.charAt(i)))
                throw new NumberFormatException("Invalid account format given");

        this.number = number;
    }


    public String getNumber() {
        return number;
    }

    public void addTransaction(Transaction transaction) {
        switch(transaction.getType()) {
            case DEPOSIT:
                if(equals(transaction.getDestinationAccount()))
                    addTransaction(TransactionType.DEPOSIT, transaction.getAmount());
                break;
            case WITHDRAW:
                if(equals(transaction.getSourceAccount()))
                    addTransaction(TransactionType.WITHDRAW, transaction.getAmount());
                break;
            case TRANSFER:
                if(equals(transaction.getSourceAccount()))
                    addTransaction(TransactionType.TRANSFER, transaction.getAmount());
                break;
            default:
                // Create and Delete Account Transactions have no amount associated to them
                break;
        }
    }

    private void addTransaction(TransactionType code, int amount) {
        if(amount < 0)
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        transactions.put(code, getTransactionAmount(code)+amount);
    }

    public int getTransactionAmount(TransactionType code) {
        if(!transactions.containsKey(code))
            return 0;
        return transactions.get(code).intValue();
    }

    @Override
    public int compareTo(Account account) {
        return number.compareTo(account.number);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Account)
            return compareTo((Account) o) == 0;
        return false;
    }

    @Override
    public String toString() {
        return number;
    }
}
