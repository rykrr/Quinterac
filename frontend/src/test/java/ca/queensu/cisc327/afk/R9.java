package ca.queensu.cisc327.afk;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class R9 extends AppTest{

	@Test
	public void testAppr9T1() throws Exception {
        //
		runAndTest(getListFromFile("./tests/r9/t1/console_input.txt"),
        		getListFromFile("./tests/r9/t1/accounts.txt"),
                getListFromFile("./tests/r9/t1/console_output.txt"),
                getListFromFile("./tests/r9/t1/expected_transactions.txt"));

	}
	
	@Test
	public void testAppr9T2() throws Exception {
		runAndTest(getListFromFile("./tests/r9/t2/console_input.txt"),
        		getListFromFile("./tests/r9/t2/accounts.txt"),
                getListFromFile("./tests/r9/t2/console_output.txt"),
                getListFromFile("./tests/r9/t2/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr9T3() throws Exception {
        
		runAndTest(getListFromFile("./tests/r9/t3/console_input.txt"),
        		getListFromFile("./tests/r9/t3/accounts.txt"),
                getListFromFile("./tests/r9/t3/console_output.txt"),
                getListFromFile("./tests/r9/t3/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr9T4() throws Exception {
        
		runAndTest(getListFromFile("./tests/r9/t4/console_input.txt"),
        		getListFromFile("./tests/r9/t4/accounts.txt"),
                getListFromFile("./tests/r9/t4/console_output.txt"),
                getListFromFile("./tests/r9/t4/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr9T5() throws Exception {
        
		runAndTest(getListFromFile("./tests/r9/t5/console_input.txt"),
        		getListFromFile("./tests/r9/t5/accounts.txt"),
                getListFromFile("./tests/r9/t5/console_output.txt"),
                getListFromFile("./tests/r9/t5/expected_transactions.txt"));
	}
}
