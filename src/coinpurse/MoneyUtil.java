package coinpurse;

import java.util.ArrayList;
import java.util.List;

public class MoneyUtil {
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

    public static void printCoins(List<Coin> coins){
        for (Coin c : coins) {
            System.out.println(c);
        }
    }

    public static void sortCoins(List<Coin> coins){
        java.util.Collections.sort(coins);
    }
}
