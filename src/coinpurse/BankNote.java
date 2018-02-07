package coinpurse;
/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */
public class BankNote implements Valuable{

    //this attribute is used to assign the next serial number of a bank note.
    private long nextSerialNumber = 1000000;
    //The value of banknote.
    private double value;
    //The currency of the banknote.
    private String currency;
    //Unique serial number.
    private long serialNumber;

    /**
     * Initialize new BankNote object
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value,String currency){
        this.value = value;
        this.currency = currency;
        this.serialNumber = nextSerialNumber++;
    }

    /**
     * Gets the currency of the bank note.
     * @return currency of the value
     */
    public String getCurrency() {
        return currency;
    }
    /**
     * Gets the value of the bank note.
     * @return amount of value
     */
    public double getValue() {
        return value;
    }
    /**
     * Gets the unique serial number of bank note.
     * @return return the serial number
     */
    public long getSerial() {
        return serialNumber;
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

        BankNote other = (BankNote)arg;

        return this.value == other.value && this.currency.equals(other.currency);
    }

    /**
     * Make a string from the object
     * @return a string representation of the argument in this class.
     */
    public String toString() {
        return String.format("%.0f-%s (note) [%d]",this.value,this.currency,this.serialNumber);
    }
}
