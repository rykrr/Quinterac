package afk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public Console() {
    	
    }
    
    //read the next line of console input
    public String readString() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String line = reader.readLine();
			return line;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
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

    // read the amount, discard all characters that are not numbers
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

    public void print(String s) {

    }

    public void println(String s) {

    }

}
