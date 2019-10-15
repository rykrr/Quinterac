package afk;

import afk.transactions.constraints.AgentConstraints;
import afk.transactions.constraints.MachineConstraints;
import afk.transactions.constraints.TransactionConstraints;

public enum SessionType {
    AGENT(new AgentConstraints()), MACHINE(new MachineConstraints());

    private TransactionConstraints constraints;

    SessionType(TransactionConstraints constraints) {
        this.constraints = constraints;
    }

    public TransactionConstraints getConstraints() {
        return constraints;
    }
}
