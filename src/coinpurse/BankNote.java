package coinpurse;
/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */
public class BankNote extends Money{

    //this attribute is used to assign the next serial number of a bank note.
    private long nextSerialNumber = 1000000;
    //Unique serial number.
    private long serialNumber;

    /**
     * Initialize new BankNote object using the super class but within
     * its own serial number.
     * @param value amount of the money
     * @param currency brand of value
     */
    public BankNote(double value,String currency){
        super(value, currency);
        this.serialNumber = nextSerialNumber++;
    }

    /**
     * Gets the unique serial number of bank note.
     * @return return the serial number
     */
    public long getSerial() {
        return serialNumber;
    }

    /**
     * Make a string from the object
     * @return a string representation of the argument in this class.
     */
    public String toString() {
        return String.format("%.0f-%s (note) [%d]",this.getValue(),this.getCurrency(),this.serialNumber);
    }
}
