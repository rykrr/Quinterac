package ca.queensu.cisc327.afk.office;

public class TransactionBuilder {

    private String  type;
    private String  source;
    private String  destination;
    private int     amount;
    private String  name;

    public TransactionBuilder() {
        // Set defaults
    }

    public void setType(String type) {
        // Input validations go here
        this.type = type;
    }

    public void setSource(String source) {
        // Input validations go here
        this.source = source;
    }

    public void setDestination(String destination) {
        // Input validations go here
        this.destination = destination;
    }

    public void setAmount(String amount) {
        // Input validations go here
        this.amount = 0; // TODO change this to int
    }

    public void setName(String name) {
        // Input validations go here
        this.name = name;
    }

}
