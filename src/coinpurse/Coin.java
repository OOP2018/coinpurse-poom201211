package coinpurse;

/**
 * Coin represents coinage (money) with a fixed value and currency.
 * @author Thanapoom Rattanathumawat
 */

public class Coin implements Comparable<Coin> {

    private double value;
    private String currency;

    public Coin(double value, String currency){
        if(value > 0){
           this.value = value;
           this.currency = currency;
        }
    }

    public double getValue(){
        return this.value;
    }

    public String getCurrency(){
        return this.currency;
    }

    public boolean equals(Object arg){

        if(arg == null)return false;

        if(arg.getClass() != this.getClass())return false;

        Coin other = (Coin)arg;

        return this.value == other.value && this.currency == other.currency;
    }

    public int compareTo(Coin coin){
        //this.getValue() = a
        //coin.getValue() = b
        if(this.getValue() > coin.getValue()){
            return 1;
        }
        else if(this.getValue() < coin.getValue()){
            return -1;
        }
        /**
         * This else statement is use to check when a == b.
         * @return 0
         */
        else{
            return 0;
        }
    }

    public String toString(){
        return String.format("%.2f-%s",value,currency);
    }

}
