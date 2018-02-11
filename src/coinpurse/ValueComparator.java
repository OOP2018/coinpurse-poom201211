package coinpurse;

import java.util.Comparator;

public class ValueComparator implements Comparator<Valuable> {
    /**
     * Compare two objects that implement Valuable.
     * If both objects have the same currency, order them by value.
     */
    public int compare(Valuable a, Valuable b) {
        // My code for comparing values.
        return Double.compare(a.getValue(),b.getValue());
    }

}
