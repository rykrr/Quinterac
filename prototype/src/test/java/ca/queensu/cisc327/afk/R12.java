package ca.queensu.cisc327.afk;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class R12 extends AppTest{

	@Test
	public void testAppr12T1() throws Exception {
        //
		runAndTest(getListFromFile("./tests/r12/t1/console_input.txt"),
        		getListFromFile("./tests/r12/t1/accounts.txt"),
                getListFromFile("./tests/r12/t1/console_output.txt"),
                getListFromFile("./tests/r12/t1/expected_transactions.txt"));

	}
	
	@Test
	public void testAppr12T2() throws Exception {
		runAndTest(getListFromFile("./tests/r12/t2/console_input.txt"),
        		getListFromFile("./tests/r12/t2/accounts.txt"),
                getListFromFile("./tests/r12/t2/console_output.txt"),
                getListFromFile("./tests/r12/t2/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T3() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t3/console_input.txt"),
        		getListFromFile("./tests/r12/t3/accounts.txt"),
                getListFromFile("./tests/r12/t3/console_output.txt"),
                getListFromFile("./tests/r12/t3/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T4() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t4/console_input.txt"),
        		getListFromFile("./tests/r12/t4/accounts.txt"),
                getListFromFile("./tests/r12/t4/console_output.txt"),
                getListFromFile("./tests/r12/t4/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T5() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t5/console_input.txt"),
        		getListFromFile("./tests/r12/t5/accounts.txt"),
                getListFromFile("./tests/r12/t5/console_output.txt"),
                getListFromFile("./tests/r12/t5/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T6() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t6/console_input.txt"),
        		getListFromFile("./tests/r12/t6/accounts.txt"),
                getListFromFile("./tests/r12/t6/console_output.txt"),
                getListFromFile("./tests/r12/t6/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T7() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t7/console_input.txt"),
        		getListFromFile("./tests/r12/t7/accounts.txt"),
                getListFromFile("./tests/r12/t7/console_output.txt"),
                getListFromFile("./tests/r12/t7/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T8() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t8/console_input.txt"),
        		getListFromFile("./tests/r12/t8/accounts.txt"),
                getListFromFile("./tests/r12/t8/console_output.txt"),
                getListFromFile("./tests/r12/t8/expected_transactions.txt"));
	}
	
	@Test
	public void testAppr12T9() throws Exception {
        
		runAndTest(getListFromFile("./tests/r12/t9/console_input.txt"),
        		getListFromFile("./tests/r12/t9/accounts.txt"),
                getListFromFile("./tests/r12/t9/console_output.txt"),
                getListFromFile("./tests/r12/t9/expected_transactions.txt"));
	}
}
