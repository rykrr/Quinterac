package ca.queensu.cisc327.afk.transactions.constraints;

import ca.queensu.cisc327.afk.transactions.TransactionType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransactionConstraints {

    private Set<TransactionType> allowedTransactionTypes = new HashSet<TransactionType>();

    private Map<TransactionType, Integer> perTransactionLimits   = new HashMap<TransactionType, Integer>();
    private Map<TransactionType, Integer> dailyLimits            = new HashMap<TransactionType, Integer>();

    protected void addAllowedTransactionType(TransactionType type) {
        allowedTransactionTypes.add(type);
    }

    protected void setPerTransactionLimit(TransactionType type, int limit) {
        perTransactionLimits.put(type, limit);
    }

    protected void setDailyLimit(TransactionType type, int limit) {
        dailyLimits.put(type, limit);
    }

    private int getNullable(Map<TransactionType, Integer> map, TransactionType type) {
        if (map.containsKey(type))
            return map.get(type).intValue();
        return 0;
    }

    public int getPerTransactionLimit(TransactionType type) {
        return getNullable(perTransactionLimits, type);
    }

    public int getDailyLimit(TransactionType type) {
        return getNullable(dailyLimits, type);
    }

    public boolean isAllowedTransaction(TransactionType type) { return allowedTransactionTypes.contains(type); }

    public Set<TransactionType> getAllowedTransactionTypes() {
        return new HashSet<>(allowedTransactionTypes);
    }
}
