package coinpurse;

/**
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */

public class Coin extends Money{

    /**
     * Initializing parameters using the super class.
     * @param value
     * @param currency
     */
    public Coin(double value,String currency){
        super(value, currency);
    }

    /**
     * This method is used to return a string in a specific formating
     * of value and currency.
     * @return String in a specific format
     */

    public String toString(){
        return String.format("%.2f-%s",this.getValue(),this.getCurrency());
    }

}
