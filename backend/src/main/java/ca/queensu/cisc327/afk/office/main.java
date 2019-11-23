package ca.queensu.cisc327.afk.office;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import ca.queensu.cisc327.afk.office.transactions.TransactionBuilder;
import org.apache.commons.io.FileUtils;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	
	//Read each transaction from the merged transaction file, check constraints
	public static ArrayList<String[]> readTransactiontoList(String file) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				if (line.length() > 61) throw new exceedMaxLenthException("Longer than 61");
				String[] tokens = line.split(" ");
				String[] string = {tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]};
				if (!tokens[0].matches("DEP|WDR|XFR|NEW|DEL|EOS")) throw new transactionCodeException("Not the correct transaction code");
				for (int i=1; i <=3; i++) {
					if (tokens[i].contains(" ")) throw new moreThanOneSpaceException("More than one space");
				}
				list.add(string);
			}
		if (list.get(list.size()-1)[0] != "EOS") throw new transactionCodeException("Last line is not EOS");
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
	public static ArrayList<String[]> readMasterAccountstoList(String filepath) {
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
	public static TransactionBuilder transactionReader(String filepath) {
		ArrayList<String[]> list = readTransactiontoList(filepath);
		TransactionBuilder trans = new TransactionBuilder();
		for (String[] line: list) {
			trans.setType(line[0]);
			trans.setSource(line[1]);
			trans.setAmount(line[2]);
			trans.setDestination(line[3]);
			trans.setName(line[4]);
		}
		return trans;
	}
	
	//Write to file, save the file to log before overwrite
	public static void writetoFile(String filepath, ArrayList<String> list) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
			LocalDateTime now = LocalDateTime.now();
			File source = new File(filepath);
			File destnation = new File("logs/" + filepath + dtf.format(now) + ".txt");
			FileUtils.copyDirectory(source, destnation);
			PrintWriter writer = new PrintWriter(source);
			writer.print("");
			Writer output = new BufferedWriter(new FileWriter(filepath, true));
			for (String line : list) {
				output.write(line + "\n");
			}
		} catch (IOException e) {
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
	
	public static void backend() {
		
	}

}
