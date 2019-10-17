package afk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Console {

    public Console() {

    }

    public String readString() {
    	//read the next line of console input
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	try {
			String line = reader.readLine();
			return line;
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
    }

    public String readAccount() {
        throw new NumberFormatException();
    }

    public int readAmount() {
        throw new NumberFormatException();
    }

    public void writeString(String s) {

    }

}
