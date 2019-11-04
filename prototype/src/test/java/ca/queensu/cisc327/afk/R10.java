package ca.queensu.cisc327.afk;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class R10 extends AppTest{

	@Test
	public void testAppr10T1() throws Exception {
        //
		runAndTest(getListFromFile("./tests/r10/t1/console_input.txt"),
        		getListFromFile("./tests/r10/t1/accounts.txt"),
                getListFromFile("./tests/r10/t1/console_output.txt"),
                getListFromFile("./tests/r10/t1/expected_transactions.txt"));

	}
	
	@Test
	public void testAppr10T2() throws Exception {
		runAndTest(getListFromFile("./tests/r10/t2/console_input.txt"),
        		getListFromFile("./tests/r10/t2/accounts.txt"),
                getListFromFile("./tests/r10/t2/console_output.txt"),
                getListFromFile("./tests/r10/t2/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T3() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t3/console_input.txt"),
        		getListFromFile("./tests/r10/t3/accounts.txt"),
                getListFromFile("./tests/r10/t3/console_output.txt"),
                getListFromFile("./tests/r10/t3/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T4() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t4/console_input.txt"),
        		getListFromFile("./tests/r10/t4/accounts.txt"),
                getListFromFile("./tests/r10/t4/console_output.txt"),
                getListFromFile("./tests/r10/t4/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T5() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t5/console_input.txt"),
        		getListFromFile("./tests/r10/t5/accounts.txt"),
                getListFromFile("./tests/r10/t5/console_output.txt"),
                getListFromFile("./tests/r10/t5/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T6() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t6/console_input.txt"),
        		getListFromFile("./tests/r10/t6/accounts.txt"),
                getListFromFile("./tests/r10/t6/console_output.txt"),
                getListFromFile("./tests/r10/t6/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T7() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t7/console_input.txt"),
        		getListFromFile("./tests/r10/t7/accounts.txt"),
                getListFromFile("./tests/r10/t7/console_output.txt"),
                getListFromFile("./tests/r10/t7/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T8() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t8/console_input.txt"),
        		getListFromFile("./tests/r10/t8/accounts.txt"),
                getListFromFile("./tests/r10/t8/console_output.txt"),
                getListFromFile("./tests/r10/t8/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr10T9() throws Exception {
        
		runAndTest(getListFromFile("./tests/r10/t9/console_input.txt"),
        		getListFromFile("./tests/r10/t9/accounts.txt"),
                getListFromFile("./tests/r10/t9/console_output.txt"),
                getListFromFile("./tests/r10/t9/expected_transactions.txt"));
	}
}
