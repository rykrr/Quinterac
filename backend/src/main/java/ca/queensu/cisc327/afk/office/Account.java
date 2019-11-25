package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.Transaction;

public class Account implements Comparable<Account>, Cloneable {

    private String name;
    private String number;

    private int balance;


    public Account(String name, String number, int balance) {
        if(number.length() != 7)
            throw new NumberFormatException("Account numbers must be 7 digits in length");

        if(number.charAt(0) == '0' && !number.equals("0000000"))
            throw new NumberFormatException("Account numbers must not start with 0");

        for(int i = 0; i < 7; i++)
            if(!Character.isDigit(number.charAt(i)))
                throw new NumberFormatException("Invalid account format given");

        this.number  = number;
        this.balance = balance;
        this.name    = name;
    }


    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    private void adjustBalance(int value) throws AccountConstraintViolation {
        if(balance + value < 0)
            throw new AccountConstraintViolation("Account balance for " + number + " is negative.");
        balance += value;
    }


    public void applyTransaction(Transaction t) throws AccountConstraintViolation {
        if(t.getAmount() < 0)
            throw new IllegalArgumentException("Transaction value must not be negative!");

        // Subtract the amount from the account balance if this
        // account is the source account for this transaction.
        // (Applies to withdrawals and transfers)
        if(number.equals(t.getSourceNumber()))
            adjustBalance(-t.getAmount());

        // Add the amount to the account balance if this account
        // is the destination account for this transaction.
        // (Applies to deposits and transfers)
        else if(number.equals(t.getDestinationNumber()))
            adjustBalance(t.getAmount());


        else System.err.println("Warning: Transaction doesn't apply to account");
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
        return number + ' ' + (balance == 0? "000" : balance) + ' ' + name;
    }


    @Override
    public Account clone() {
        return new Account(name, number, balance);
    }
}
