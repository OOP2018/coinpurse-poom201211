package coinpurse;

/**
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */

public class Coin implements Comparable<Coin>,Valuable {

    /** value of the coin*/
    private double value;
    /** currency of the coin*/
    private String currency;

    /**
     * Initializing parameters.
     * @param value
     * @param currency
     */
    public Coin(double value, String currency){
        if(value > 0){
           this.value = value;
           this.currency = currency;
        }
    }

    /**
     * This method is created to get the value of coin.
     * @return value of coin.
     */

    public double getValue(){
        return this.value;
    }

    /**
     * This string method is use to get the currency.
     * @return currency of type coin.
     */

    public String getCurrency(){
        return this.currency;
    }

    /**
     * Comparing two objects if they are the same returning
     * a boolean type data.
     * @param arg
     * @return true of false
     */

    public boolean equals(Object arg){

        if(arg == null)return false;

        if(arg.getClass() != this.getClass())return false;

        Coin other = (Coin)arg;

        return this.value == other.value && this.currency.equals(other.currency);
    }

    /**
     * This method is used to order the coins from smallest to largest.
     * @param coin
     * @return x > 0 , x < 0 , x == 0
     */

    public int compareTo(Coin coin){
        //this.getValue() = a
        //coin.getValue() = b
        if(this.getValue() > coin.getValue()){
            return 1;
        }
        else if(this.getValue() < coin.getValue()){
            return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * This method is used to return a string in a specific formating
     * of value and currency.
     * @return String in a specific format
     */

    public String toString(){
        return String.format("%.2f-%s",value,currency);
    }

}
