# Test Plan

See `tests/test_cases.pdf` for test case spreadsheet

## Test Case Authors
| Tests                        | Author         |
| ---------------------------- | -------------- |
| login, logout                | Brianna Li     |
| deleteacct, createacct       | Shihao Lu      |
| deposit, witdraw, transfer   | Ryan Kerr      |

## Summary
Our test plan involves the mixed-use of Bash shell scripts and Expect scripts to run each test case. Each test case will be organized in the file structure below. The test script will traverse each directory and run an expect script which reads in the list of expected outputs (console_outputs.txt) and respond with the appropriate inputs (console_inputs.txt). The scripts will note a failure if the expected output differs from the actual input and write the error to error.txt. If a transactions.txt is specified by the test, the output from the program will be diff'd. Any differences will be noted as an error and be saved in differences.txt.

All program runs will be summarized in summary.txt.

## File Structure
	* tests
		* feature name
			* "machine", "agent", or "general"
				* test group
					* test name
						* info.txt  [ Contains data such as the name of the test case and what it tests ]
						* console_input.txt [ The list of commands being input to program ]
						* console_output.txt [ The list of outputs to expect from the program ]
						* accounts_list.txt [ The valid accounts list ]
						* expected_transactions.txt [ The expected transaction file if applicable ]
	* completed_tests
		* timestamp
			* summary.txt
			* feature name
				* "machine", "agent", or "general"
					* test group
						* test name
							* transactions.txt
							* difference.txt (iff there is a difference!)
							* error.txt (iff there is an error, details where the error occured)
  

## Typical Run
```
- Welcome to AFK Quinterac!
- Type ‘login’ to begin
- >logout
- Error: invalid command
Type ‘login’ to begin
> login
Please enter login type (‘machine’ or ‘agent’)
>abcd
Error: invalid login type
Please enter login type (‘machine’ or ‘agent’)
> machine
Successfully logged in as machine. Please choose one of the services: ‘withdraw’, ‘deposit’, ‘transfer’
Type ‘logout’ anytime to exit the system
atm>login
Error: already logged in as machine. Please log out first
atm> createacct
Error: you do not have sufficient privileges to create an account, please log in as agent 
atm> deleteacct
Error: you do not have sufficient privileges to delete an account, please log in as agent
atm> deposit
Please enter an account to deposit to (enter ‘0000000’ to cancel)
account #1000001
Error: account #1000001 does not exist.
Please enter an account to deposit to (enter ‘0000000’ to cancel)
account #1000000
Enter the amount to deposit (enter ‘0’ to cancel)
amount ¢100
Successfully deposited ¢100 into account #1000000
atm>withdraw
Please enter an account to withdraw  from (enter ‘0000000’ to cancel)
account #0000000
Operation canceled
atm>transfer
Please enter an account to transfer from (enter ‘0000000’ to cancel)
account #0000000
Operation canceled
atm>  logout
You have successfully logged out
>login
Please enter login type (‘machine’ or ‘agent’)
> agent
Successfully logged in as agent. Please choose one of the services: ‘withdraw’, ‘deposit’, ‘transfer’, ‘createacct’, ‘deleteacct’
Type ‘logout’ anytime to exit the system
agent> createacct
Please enter a seven-digit account number not beginning with 0 (enter ‘0000000’ to cancel)


agent> deleteacct
Please enter the account number to be deleted (enter ‘0000000’ to cancel)
```
