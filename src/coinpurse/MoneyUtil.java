package coinpurse;

/**
 * This class is created to test the coin class.
 * Usses most of the methods in the coin class.
 * @author Thanapoom Rattanathumawat
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MoneyUtil {

    /**
     * Main method.
     * @param args
     */

    public static void main(String[] args){

        List<Valuable> valuables = new ArrayList<Valuable>();

        valuables.add(new Coin(9.2, "Baht"));
        valuables.add(new Coin(10, "Baht"));
        valuables.add(new Coin(5.4, "Baht"));
        valuables.add(new Coin(50, "Baht"));

        printValuable(valuables);
        //The code below checks the compareTo() method in the Coin class.
        sortValuable(valuables);
        System.out.println("Sorted:");
        printValuable(valuables);

    }

    /**
     * Print one coin at a time using for each loop.
     * @param List of coins
     */

    public static void printValuable(List<Valuable> valuable){
        for (Valuable v : valuable) {
            System.out.println(v);
        }
    }

    /**
     * Sorts the coin into an given order.
     * @param List of coins
     */

    public static void sortValuable(List<Valuable> valuable){
        Comparator<Valuable> comp = new ValueComparator();
        java.util.Collections.sort(valuable,comp);
    }

    /**
     * This method is created to add the coins by the same currency
     * to a list and return them.
     * @param List of coins
     * @param currency
     * @return List of coins that contains only the coins from the parameter
     */

    public static List<Valuable> filterByCurrency(List<Valuable> valuable, String currency) {

        List<Valuable> listFilter = new ArrayList<Valuable>();

        for (Valuable v : valuable) {
            if (v.getCurrency().equals(currency)){
                listFilter.add(v);
            }
        }

        return listFilter;
    }

}
