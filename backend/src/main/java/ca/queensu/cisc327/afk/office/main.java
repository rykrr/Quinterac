package ca.queensu.cisc327.afk.office;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}
	public static void runBackend() {
		
	}
	
	public static ArrayList<String[]> readTransactiontoList(String file) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
		FileInputStream stream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
			while ((line = br.readLine()) != null)   {
				String[] tokens = line.split(" ");
				String[] string = {tokens[0], tokens[1], tokens[2], tokens[3]};
				list.add(string);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No line found in file.");
		}
        return list;
	}
	
	public static Transaction transactionReader(String file) {
		ArrayList<String[]> list = readTransactiontoList(file);
		Transaction trans = new Transaction();
		trans
	}
	
	public static void writetoFile(File file) {
		
	}
	
	public static void backend() {
		
	}

}
