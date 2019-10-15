package afk.transactions.constraints;

import afk.transactions.TransactionType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TransactionConstraints {

    private Set<TransactionType> allowedTransactionTypes = new HashSet<TransactionType>();

    private Map<TransactionType, Integer> perTransactionLimits   = new HashMap<TransactionType, Integer>();
    private Map<TransactionType, Integer> dailyLimits            = new HashMap<TransactionType, Integer>();

    protected void addAllowedTransactionType(TransactionType code) {
        allowedTransactionTypes.add(code);
    }

    protected void setPerTransactionLimit(TransactionType code, int limit) {
        perTransactionLimits.put(code, limit);
    }

    protected void setDailyLimit(TransactionType code, int limit) {
        dailyLimits.put(code, limit);
    }

    private int getNullable(Map<TransactionType, Integer> map, TransactionType code) {
        if (map.containsKey(code))
            return map.get(code).intValue();
        return 0;
    }

    public int getPerTransactionLimit(TransactionType code) {
        return getNullable(perTransactionLimits, code);
    }

    public int getDailyLimit(TransactionType code) {
        return getNullable(dailyLimits, code);
    }
}
