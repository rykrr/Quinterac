package ca.queensu.cisc327.afk;

import static org.junit.Assert.assertEquals;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

import ca.queensu.cisc327.afk.Main;

public class AppTest {
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	//@Test
	public void testAppR1T0() throws Exception {
		runAndTest(getListFromFile("./tests/r1/t1/console_input.txt"),
        		getListFromFile("./tests/r1/t1/accounts.txt"),
                getListFromFile("./tests/r1/t1/console_output.txt"),
                getListFromFile("./tests/r1/t1/expected_transactions.txt"));
	}
	
    //@Test
    public void testAppR1T1() throws Exception {
        runAndTest(getListFromFile("./tests/r1/t1/console_input.txt"),
        		getListFromFile("./tests/r1/t1/accounts.txt"),
                getListFromFile("./tests/r1/t1/console_output.txt"),
                Arrays.asList(""));
    }
    
   // @Test
    public void testAppR2() throws Exception {
    	runAndTest(Arrays.asList("login", "machine", "logout"), //
    			Arrays.asList("0000000", "1000000", "2000000"), //
    			Arrays.asList("Type 'login' to begin", "> Please enter a login type (‘machine’ or ‘agent’), type 'cancel' to cancel",
    					"> Welcome! You have successfully logged in as machine", "Available commands: withdraw, transfer, logout, deposit", "machine> Successfully logged out" , "> "), //
    			Arrays.asList(""));
    }
    
    public List<String> getListFromFile(String path) throws IOException, FileNotFoundException {
    	File file = new File(path);
    	BufferedReader reader = new BufferedReader(new FileReader(file));
    	List<String> expected = new ArrayList<String>();
    	String line;
    	while ((line= reader.readLine()) != null) {
    		expected.add(line);
    	}
    	return expected;
    }

    /**
     * Helper function to run the main function and verify the output
     * 
     * @param terminal_input                 A list of string as the terminal input
     *                                       to run the program
     * 
     * @param valid_accounts                 A list of valid accounts to be used for
     *                                       the test case
     * 
     * @param expected_terminal_tails        A list of string expected at the tail
     *                                       of terminal output
     * 
     * @param expected_transaction_summaries A list of string expected to be in the
     *                                       output transaction summary file
     * 
     * @throws Exception
     */
    public void runAndTest(List<String> terminal_input, //
            List<String> valid_accounts, //
            List<String> expected_terminal_tails, //
            List<String> expected_transaction_summaries)
			throws Exception {
        // setup parameters for the program to run
        // create temporary files
        File valid_account_list_file = File.createTempFile("valid-accounts", ".tmp");
        Files.write(valid_account_list_file.toPath(), String.join("\n", valid_accounts).getBytes());

        File transaction_summary_file = File.createTempFile("transactions", ".tmp");

        String[] args = { valid_account_list_file.getAbsolutePath(), transaction_summary_file.getAbsolutePath() };

        // setup user input
        String userInput = String.join(System.lineSeparator(), terminal_input);
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);

        // setup stdin & stdout:
        PrintStream  system=System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        // run the program
        try {
        exit.expectSystemExit();
        Main.main(args);} 
        finally {

        // capture terminal outputs:
        String[] printed_lines = outContent.toString().split("[\r\n]+");
        System.setOut(system);
        for (int i = 0; i < printed_lines.length; i++) {
        }
        // compare the tail of the terminal outputs:
        int diff = printed_lines.length - expected_terminal_tails.size();
        for (int i = 0; i < expected_terminal_tails.size(); ++i) {
            assertEquals(expected_terminal_tails.get(i), printed_lines[i + diff]);
        }
        
        // compare output file content to the expected content
        String actual_output = new String(Files.readAllBytes(transaction_summary_file.toPath()), "UTF-8");
        String[] lines = actual_output.split("[\r\n]+");
        if (!expected_transaction_summaries.isEmpty()) {
        	for (int i = 0; i < lines.length; ++i)
        		assertEquals(expected_transaction_summaries.get(i), lines[i]);
        }
        System.out.println("done");}
    }

    /**
     * Retrieve the absolute path of the files in the resources folder
     * 
     * @param relativePath The file's relative path in the resources folder
     *                     (/test/resources)
     * @return the absolute path of the file in the resource folder.
     */
    String getFileFromResource(String relativePath) {
        return new File(this.getClass().getResource(relativePath).getFile()).getAbsolutePath();
    }
}
