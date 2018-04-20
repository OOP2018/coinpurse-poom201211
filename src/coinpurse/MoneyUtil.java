package coinpurse;

/**
 * This class is created to test the coin class.
 * Usses most of the methods in the coin class.
 * @author Thanapoom Rattanathumawat
 */

import java.util.*;

public class MoneyUtil {

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

    public static void sortMoney(List<? extends Valuable> valuable){
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

    public static <E extends Valuable> List<E> filterByCurrency(List<E> valuable, String currency) {

        List<E> listFilter = new ArrayList<E>();

        for (E v : valuable) {
            if (v.getCurrency().equals(currency)){
                listFilter.add(v);
            }
        }

        return listFilter;
    }

    /**
     * Return the larger argument, based on sort order, using
     * the objects' own compareTo method for comparing.
     * @param args one more more Comparable objects to compare.
     * @return the argument that would be last if sorted the elements.
     * @throws IllegalArgumentException if no arguments given.
     */

    public static <E extends Comparable<Valuable>>E max(E ... args){
        try{
            E max = args[0];
            for(int i = 0; i < args.length; i++){
                if((args[i].compareTo((Valuable)max))> 0){
                    max = args[i];
                }
            }
            return max;
        }catch(IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Main method for testing methods.
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
        sortMoney(valuables);
        System.out.println("Sorted:");
        printValuable(valuables);

        System.out.println("-------------------");

        Money m1 = new BankNote(100, "Baht");
        Money m2 = new Coin(20, "Baht");
        Money m3 = new BankNote(500, "Baht");
        Money max2 = MoneyUtil.max(m1, m2, m3);

        System.out.println(max2);// test print

        List<Coin> coins = Arrays.asList( new Coin(5, "Baht"), new Coin(0.1, "Ringgit"), new Coin(5, "Cent"));
        List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht");

        System.out.println(result);// test print



    }


}
