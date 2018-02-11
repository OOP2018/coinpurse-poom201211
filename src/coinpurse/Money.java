package coinpurse;

public class Money implements Valuable{

    //The value of classes extending money.
    private double value;
    //The currency of classes extending money.
    private String currency;

    public Money(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    /**
     * Gets the currency of the object.
     * @return currency of the value
     */
    public String getCurrency() {
        return currency;
    }

    /**
* Gets the value of the object.
     * @return amount of value
     */
    public double getValue() {
        return value;
    }

    /**
     * This method is used to order the coins from smallest to largest.
     * @param v
     * @return x > 0 , x < 0 , x == 0
     */
    public int compareTo(Valuable v) {
        //this.getValue() = a
        //v.getValue() = b
        return Double.compare(this.getValue(),v.getValue());
    }

    /**
     * Test the object objects if thet have the same
     * value or currency.
     * @param arg Used to text with the current object.
     * @return true, false depending on if the objects are equal.
     */
    public boolean equals(Object arg){

        if(arg == null)return false;

        if(arg.getClass() != this.getClass())return false;

        Valuable other = (Valuable)arg;

        return this.getValue() == other.getValue() && this.getCurrency().equals(other.getCurrency());
    }
}
