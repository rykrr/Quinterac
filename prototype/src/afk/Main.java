package afk;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import afk.AccountListReader.AccountListReaderException;
import afk.transactions.Transaction;

public class Main {

    /**
     * @param args
     *  args[0] Ignore
     *  args[1] Input: Path to the valid accounts list
     *  args[2] Output: Transaction summary file
     */
    public static void main(String[] args) {
        // initialize the console for the banking system
    	Console console = new Console();
    	// keep checking user input until the user types in exit
    	while (true) {
        new Main(console, args[0], args[1]);
    	}
    }
    
    public void writeFile(String[] summary, String filepath) {
    	try {
    		// write the transaction summary to the output file
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
    	System.out.println("Type 'login' to begin");
    	String start = console.readString();
    	// read console for "login" command
    	if(start.equals("login")) {
    		List<Account> accountlist;
			try {
				// get valid account list
				accountlist = AccountListReader.read(validAccountsListPath);
    		boolean sessionfinished = false;
    		while (!sessionfinished) {
    		System.out.println("Please enter login type (‘machine’ or ‘agent’)");
    		String input = console.readString();

    		// open a session for Machine or Agent, wait and check if the session finished all transactions
    		if(input.equals("machine")) {
    			Session session = new Session(SessionType.MACHINE, accountlist);
    			List<Transaction> transactions = session.run(console);

    			// get the summary array of the session
                String[] summary = new String[transactions.size()];

                for(int i = 0; i < transactions.size(); i++)
                	summary[i] = transactions.get(i).toString();

    			writeFile(summary, transactionSummaryPath);
    		}
    		else if (input.equals("agent")) {
    			Session session = new Session(SessionType.AGENT, accountlist);
				List<Transaction> transactions = session.run(console);

				// get the summary array of the session
				String[] summary = new String[transactions.size()];

				for(int i = 0; i < transactions.size(); i++)
					summary[i] = transactions.get(i).toString();

				writeFile(summary, transactionSummaryPath);
    		}
    		else if (input.length()>0) {
    			System.out.println("Error: invalid login type");
    		}
    		}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (AccountListReaderException e) {
				e.printStackTrace();
			}
    		}
    	// able to exit after logged out
    	else if (start.equals("exit")) {
    		System.exit(0);
    	}
    	else if (start.length()>0) {
    		System.out.println("Error: invalid command");
    	}
    }
}
