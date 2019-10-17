package afk;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

public class Main {

    /**
     * @param args
     *  args[0] Ignore
     *  args[1] Input: Path to the valid accounts list
     *  args[2] Output: Transaction summary file
     */
    public static void main(String[] args) {
        // Parse arguments
        // Repeat forever?
    	Console console = new Console();
    	while (true) {
        new Main(console, args[0], args[1]);
    	}
    }
    
    public void writeFile(String[] summary, String filepath) {
    	try {
			Writer output = new BufferedWriter(new FileWriter("filepath", true));
			for (String line : summary) {
				output.append(line);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Main(Console console, String validAccountsListPath, String transactionSummaryPath) {
        // Initialize the console (We can use our custom Console class)
        // Read console for "login" command
    	System.out.println("Type 'login' to begin");
    	String start = console.readString();
    	if(start.equals("login")) {
    		accountlist;
    		boolean sessionfinished = false;
    		while (!sessionfinished) {
    		System.out.println("Please enter login type (‘machine’ or ‘agent’)");
    		String input = console.readString();
    		if(input.equals("machine")) {
    			Session session = new Session(SessionType.MACHINE, accountlist);
    			while(!sessionfinished) {
    				sessionfinished = session.finished;
        			}
    			String[] summary = session.summary;
    			writeFile(summary, transactionSummaryPath);
    			}
    		else if (input.equals("agent")) {
    			Session session = new Session(SessionType.AGENT, accountlist);
    			while(!sessionfinished) {
    				sessionfinished = session.finished;
        			}
    			String[] summary = session.summary;
    			writeFile(summary, transactionSummaryPath);
    		}
    		else if (input.equals("logout")) {
    			String[] array = new String[1];
    			array[0] = "EOS 0000000 000 0000000 ***";
    			sessionfinished = true;
    			writeFile(array, transactionSummaryPath);
    		}
    		else if (input.length()>0) {
    			System.out.println("Error: invalid login type");
    			continue;
    		}
    		else 
    			continue;
    		}
    		}
        // Read valid accounts list file
        // Create a Session(validAccounts)
        // Read commands
        // Pass commands to Session

        // on logout, call session.logout
        // write transaction summary list (from logout) to transaction summary file
    	// able to exit after logged out
    	else if (start.equals("exit")) {
    		System.exit(0);
    	}
    	else if (start.length()>0) {
    		System.out.println("Error: invalid command");
    	}
    	else
    		continue;
    }
}
