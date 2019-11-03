package afk;

import afk.transactions.constraints.AgentConstraints;
import afk.transactions.constraints.MachineConstraints;
import afk.transactions.constraints.TransactionConstraints;

public enum SessionType {
    AGENT("agent", new AgentConstraints()), MACHINE("machine", new MachineConstraints());

    private String name;
    private TransactionConstraints constraints;

    SessionType(String name, TransactionConstraints constraints) {
        this.name = name;
        this.constraints = constraints;
    }

    public String getName() {
        return name;
    }

    public TransactionConstraints getConstraints() {
        return constraints;
    }
}
