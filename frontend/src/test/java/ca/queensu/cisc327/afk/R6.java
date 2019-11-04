package ca.queensu.cisc327.afk;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class R6 extends AppTest{

	@Test
	public void testAppr6T1() throws Exception {
        //
		runAndTest(getListFromFile("./tests/r6/t1/console_input.txt"),
        		getListFromFile("./tests/r6/t1/accounts.txt"),
                getListFromFile("./tests/r6/t1/console_output.txt"),
                getListFromFile("./tests/r6/t1/expected_transactions.txt"));

	}
	
	@Test
	public void testAppr6T2() throws Exception {
		runAndTest(getListFromFile("./tests/r6/t2/console_input.txt"),
        		getListFromFile("./tests/r6/t2/accounts.txt"),
                getListFromFile("./tests/r6/t2/console_output.txt"),
                getListFromFile("./tests/r6/t2/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr6T3() throws Exception {
        
		runAndTest(getListFromFile("./tests/r6/t3/console_input.txt"),
        		getListFromFile("./tests/r6/t3/accounts.txt"),
                getListFromFile("./tests/r6/t3/console_output.txt"),
                getListFromFile("./tests/r6/t3/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr6T4() throws Exception {
        
		runAndTest(getListFromFile("./tests/r6/t4/console_input.txt"),
        		getListFromFile("./tests/r6/t4/accounts.txt"),
                getListFromFile("./tests/r6/t4/console_output.txt"),
                getListFromFile("./tests/r6/t4/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr6T5() throws Exception {
        
		runAndTest(getListFromFile("./tests/r6/t5/console_input.txt"),
        		getListFromFile("./tests/r6/t5/accounts.txt"),
                getListFromFile("./tests/r6/t5/console_output.txt"),
                getListFromFile("./tests/r6/t5/expected_transactions.txt"));
	}
}
