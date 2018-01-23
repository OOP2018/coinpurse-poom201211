package coinpurse;

/**
 * This class is created to test the coin class.
 * Usses most of the methods in the coin class.
 * @author Thanapoom Rattanathumawat
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MoneyUtil {

    /**
     * Main method.
     * @param args
     */

    public static void main(String[] args){

        List<Coin> coins = new ArrayList<Coin>();

        coins.add(new Coin(9.2, "Baht"));
        coins.add(new Coin(10, "Baht"));
        coins.add(new Coin(5.4, "Baht"));
        coins.add(new Coin(50, "Baht"));

        printCoins(coins);
        //The code below checks the compareTo() method in the Coin class.
        sortCoins(coins);
        System.out.println("Sorted:");
        printCoins(coins);

    }

    /**
     * Print one coin at a time using for each loop.
     * @param List of coins
     */

    public static void printCoins(List<Coin> coins){
        for (Coin c : coins) {
            System.out.println(c);
        }
    }

    /**
     * Sorts the coin into an given order.
     * @param List of coins
     */

    public static void sortCoins(List<Coin> coins){
        java.util.Collections.sort(coins);
    }

    /**
     * This method is created to add the coins by the same currency
     * to a list and return them.
     * @param List of coins
     * @param currency
     * @return List of coins that contains only the coins from the parameter
     */

    public static List<Coin> filterByCurrency(List<Coin> coins, String currency) {

        List<Coin> listFilter = new ArrayList<Coin>();

        for (Coin c : coins) {
            if (c.getCurrency().equals(currency)){
                listFilter.add(c);
            }
        }

        return listFilter;
    }

}
