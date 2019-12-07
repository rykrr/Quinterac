package ca.queensu.cisc327.afk.office;

import ca.queensu.cisc327.afk.office.transactions.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String[] args) {
		if(args.length != 3) {
			System.err.println("Error: Insufficient arguments.");
			System.err.println("Usage: backOffice <master account list> <transactions> <output account list>");
		}
		
		String mergedTransactionsFile	= args[0];
		String masterAccountsFile		= args[1];
		String validAccountsFile		= args[2];
		
		ArrayList<Transaction> transactions = transactionReader(mergedTransactionsFile);
		ArrayList<Account> accounts = accountReader(masterAccountsFile);

		BackOffice office = new BackOffice(accounts, transactions);

		List<Account> outputAccounts = office.execute();

		List<String> masterAccountsList	= new ArrayList<>();
		List<String> validAccountsList	= new ArrayList<>();
		
		for(Account account : outputAccounts) {
			masterAccountsList.add(account.toString());
			validAccountsList.add(account.getNumber());
		}
		
		writetoFile(masterAccountsFile, masterAccountsList, true);
		writetoFile(validAccountsFile, validAccountsList, false);
	}
	
	//Read each transaction from the merged transaction file, check constraints
	public static ArrayList<String[]> readTransactionList(String file) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				if (line.length() > 61) throw new exceedMaxLenthException("Longer than 61");
				String[] tokens = line.split(" ");

				for(int i = 0; i < tokens.length; i++)
					System.out.println(i + " " + tokens[i]);

				String[] string = {tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]};
				if (!tokens[0].matches("DEP|WDR|XFR|NEW|DEL|EOS")) throw new transactionCodeException("Not the correct transaction code");
				for (int i=1; i <=3; i++) {
					if (tokens[i].contains(" ")) throw new moreThanOneSpaceException("More than one space");
				}
				list.add(string);

				if(tokens[0].equals("EOS"))
					break;
			}
		if (!list.get(list.size()-1)[0].equals("EOS")) throw new transactionCodeException("Last line is not EOS");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No line found in file.");
		} catch (exceedMaxLenthException e) {
			e.printStackTrace();
		} catch (transactionCodeException e) {
			e.printStackTrace();
		} catch (moreThanOneSpaceException e) {
			e.printStackTrace();
		}
        return list;
	}

	//Read master accounts from the master account file, check constraints
	public static ArrayList<String[]> readMasterAccountsList(String filepath) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(filepath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				if (line.length() > 47) throw new exceedMaxLenthException("Longer than 47");
				String[] tokens = line.split(" ");
				String[] string = {tokens[0], tokens[1], tokens[2]};
				for (int i=0; i <=1; i++) {
					if (tokens[i].contains(" ")) throw new moreThanOneSpaceException("More than one space");
				}
				list.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No line found in file.");
		} catch (exceedMaxLenthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (moreThanOneSpaceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
	}
	
	// Build transaction from the read in transaction file
	public static ArrayList<Transaction> transactionReader(String filepath) {
		ArrayList<String[]> list = readTransactionList(filepath);
		ArrayList<Transaction> transactions = new ArrayList<>();

		for (String[] line: list) {
			Transaction transaction =
					new Transaction(line[0], line[1], line[3], line[2], line[4]);
			transactions.add(transaction);
		}

		return transactions;
	}

	public static ArrayList<Account> accountReader(String filepath) {
		ArrayList<String[]> list = readMasterAccountsList(filepath);
		ArrayList<Account> accounts = new ArrayList<>();

		for (String[] line: list)
			accounts.add(new Account(line[2], line[0], Integer.parseInt(line[1])));

		return accounts;
	}

	// Write to file, save the file to log before overwrite
	public static void writetoFile(String filepath, List<String> list, boolean backup) {
		try {
			File source = new File(filepath);
			
			if(backup) {
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
				LocalDateTime now = LocalDateTime.now();
				File destnation = new File("logs/" + filepath + dtf.format(now) + ".txt");
				destnation.mkdirs();
				Files.copy(source.toPath(), destnation.toPath(), StandardCopyOption.REPLACE_EXISTING);
			}

			PrintWriter writer = new PrintWriter(source);
			writer.print("");
			Writer output = new BufferedWriter(new FileWriter(filepath, true));
			for (String line : list)
				output.write(line + "\n");
			output.flush();
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public static class exceedMaxLenthException extends Exception { 
	    public exceedMaxLenthException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	public static class transactionCodeException extends Exception { 
	    public transactionCodeException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
	public static class moreThanOneSpaceException extends Exception { 
	    public moreThanOneSpaceException(String errorMessage) {
	        super(errorMessage);
	    }
	}
	
}
