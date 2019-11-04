package ca.queensu.cisc327.afk;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class R4 extends AppTest{

	@Test
	public void testAppr4T1() throws Exception {
        //
		runAndTest(getListFromFile("./tests/r4/t1/console_input.txt"),
        		getListFromFile("./tests/r4/t1/accounts.txt"),
                getListFromFile("./tests/r4/t1/console_output.txt"),
                getListFromFile("./tests/r4/t1/expected_transactions.txt"));

	}
	
	@Test
	public void testAppr4T2() throws Exception {
		runAndTest(getListFromFile("./tests/r4/t2/console_input.txt"),
        		getListFromFile("./tests/r4/t2/accounts.txt"),
                getListFromFile("./tests/r4/t2/console_output.txt"),
                getListFromFile("./tests/r4/t2/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr4T3() throws Exception {
        
		runAndTest(getListFromFile("./tests/r4/t3/console_input.txt"),
        		getListFromFile("./tests/r4/t3/accounts.txt"),
                getListFromFile("./tests/r4/t3/console_output.txt"),
                getListFromFile("./tests/r4/t3/expected_transactions.txt"));
	}
}
