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

import org.apache.commons.io.FileUtils;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	
	//Read each transaction from the merged transaction file
	public static ArrayList<String[]> readTransactiontoList(String file) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				String[] tokens = line.split(" ");
				String[] string = {tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]};
				list.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No line found in file.");
		}
        return list;
	}
	
	//Read master accounts from the master account file
	public static ArrayList<String[]> readMasterAccountstoList(String filepath) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(filepath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				String[] tokens = line.split(" ");
				String[] string = {tokens[0], tokens[1], tokens[2]};
				list.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No line found in file.");
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
	
	public static void backend() {
		
	}

}
