package afk.transactions.script;

public class TransactionCancelledException extends RuntimeException {

    public TransactionCancelledException() {
        super("Transaction cancelled");
    }

    public TransactionCancelledException(String message) {
        super(message);
    }

}
