package ca.queensu.cisc327.afk;

import java.util.List;

public class FakeConsole extends Console {

    private List<String> inputs;

    public FakeConsole(List<String> inputs) {
        this.inputs = inputs;
    }

    public String readString() {
        if(inputs.isEmpty())
            return "";
        return inputs.remove(0);
    }

    // read a 7 digit account number
    public String readAccount() {
        String accountNum = readString();
        if (accountNum.length()!=7)
            throw new NumberFormatException();
        else {
            for(int i=0; i<7; i++) {
                if (Character.isDigit(accountNum.charAt(i)))
                    continue;
                else
                    throw new NumberFormatException();
            }
            return accountNum;
        }
    }

    // read the amount, discard all characters that are space or comma
    public int readAmount() {
        String amount = readString();
        for (int i=0; i<amount.length(); i++) {
            if (amount.charAt(i)==' ' || amount.charAt(i) == ',') {
                amount = amount.substring(0, i) + amount.substring(i+1,amount.length());
                if ((i)<amount.length() || amount.charAt(i)==' ' || amount.charAt(i) == ',')
                    i--;
            }
        }
        int amountNum = Integer.parseInt(amount.trim());
        return amountNum;
    }
}
