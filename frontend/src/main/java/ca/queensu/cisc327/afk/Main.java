package ca.queensu.cisc327.afk;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import ca.queensu.cisc327.afk.AccountListReader.AccountListReaderException;
import ca.queensu.cisc327.afk.transactions.Transaction;

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
    	System.out.println("Welcome to AFK Quinterac!");
    	// keep checking user input until the user types in exit
    	while (true) {
    		new Main(console, args[0], args[1]);
    	}
    }

	public void writeFile(String[] summary, String filepath) {
		
		if(summary.length == 0)
			return;
		
    	try {
    		// write the transaction summary to the output file
			Writer output = new BufferedWriter(new FileWriter(filepath, true));
			for (String line : summary) {
				output.write(line + "\n");
				System.out.println(line);
			}
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public Main(Console console, String validAccountsListPath, String transactionSummaryPath) {
    	System.out.println("Type 'login' to begin");
    	System.out.print("> ");
    	String start = console.readString();
    	// read console for "login" command
    	if(start.equals("login")) {
    		List<Account> accountlist;
			try {
				// get valid account list
				accountlist = AccountListReader.read(validAccountsListPath);
    		boolean sessionfinished = false;
    		while (!sessionfinished) {
    		System.out.println("Please enter a login type ('machine' or 'agent'), type 'cancel' to cancel");
    		System.out.print("> ");
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
    			sessionfinished = true;
    		}
    		else if (input.equals("agent")) {
    			Session session = new Session(SessionType.AGENT, accountlist);
				List<Transaction> transactions = session.run(console);

				// get the summary array of the session
				String[] summary = new String[transactions.size()];

				for(int i = 0; i < transactions.size(); i++)
					summary[i] = transactions.get(i).toString();
				
				writeFile(summary, transactionSummaryPath);
				sessionfinished = true;
    		}
    		else if (input.equals("cancel")) {
    			sessionfinished = true;
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
    		System.out.println("Exit the program");
    		System.exit(0);
    	}
    	else if (start.length()>0) {
    		System.out.println("Error: invalid command");
    	}
    }
}
