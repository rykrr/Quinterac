package afk;

public class Main {

    /**
     * @param args
     *  args[0] Ignore
     *  args[1] Input: Path to the valid accounts list
     *  args[2] Output: Transaction summary file
     */
    public static void main(String[] args) {
        // Parse arguments
        // Repeat forever?
        new Main(args[1], args[2]);
    }

    public Main(String validAccountsListPath, String transactionSummaryPath) {
        // Initialize the console (We can use our custom Console class)
        // Read console for "login" command

        // Read valid accounts list file
        // Create a Session(validAccounts)
        // Read commands
        // Pass commands to Session

        // on logout, call session.logout
        // write transaction summary list (from logout) to transaction summary file
    }

}
