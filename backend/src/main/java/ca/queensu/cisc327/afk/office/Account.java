package ca.queensu.cisc327.afk.office;

public class Account implements Comparable<Account> {

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


    public void addTransaction(Transaction t) {
        if(!(equals(t.getSourceAccount()) || equals(t.getDestinationAccount())))
            throw new IllegalArgumentException("Given transaction is irrelevant to this account!");
        addTransaction(t.getType(), t.getAmount());
    }

    private void addTransaction(TransactionType code, int amount) {
        if(amount < 0)
            throw new IllegalArgumentException("Transaction amount cannot be negative");
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
