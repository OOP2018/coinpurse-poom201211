package coinpurse;
/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */
public class BankNote extends Money{

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
    }
    /**
     * Method use to set the serial number.
     * @param serialNumber set to this attribute serialNumber
     */
    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * Gets the unique serial number of bank note.
     * @return return the serial number
     */
    public long getSerial() {
        return this.serialNumber;
    }

    /**
     * Make a string from the object
     * @return a string representation of the argument in this class.
     */
    public String toString() {
        return String.format("%.0f-%s (note) [%d]",this.getValue(),this.getCurrency(),this.serialNumber);
    }
}
