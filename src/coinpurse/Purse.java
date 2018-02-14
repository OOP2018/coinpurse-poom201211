package coinpurse;

// You will use Collections.sort() to sort the coins

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

/**
 *  A coin purse contains coins.
 *  You can insert coins, withdraw money, check the balance,
 *  and check if the purse is full.
 *  
 *  @author Thanapoom Rattanathumawat
 */
public class Purse {
    /** Collection of objects in the purse. */
    private List<Valuable> money;
    
    /** Capacity is maximum number of items the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;

    /**
     * Defining ValueComparator as a attribute of purse it doesnt make a new
     * comparator object everytime.
     */
    private Comparator<Valuable> comp = new ValueComparator();
    
    /** 
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of coins you can put in purse.
     */
    public Purse( int capacity ) {
        money = new ArrayList<Valuable>();
        this.capacity = capacity;
    }

    /**
     * Count and return the number of coins in the purse.
     * This is the number of coins, not their value.
     * @return the number of coins in the purse
     */
    public int count() {
        return money.size();
    }
    
    /** 
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
		double totalValue = 0.0;

		for(Valuable v : money){
		    totalValue += v.getValue();
        }

        return totalValue;
	}

    
    /**
     * Return the capacity of the coin purse.
     * @return the capacity
     */

    public int getCapacity() { 
		return this.capacity;
	}
    
    /** 
     *  Test whether the purse is full.
     *  The purse is full if number of items in purse equals
     *  or greater than the purse capacity.
     *  @return true if purse is full.
     */
    public boolean isFull() {
        return (this.count() >= this.capacity);
    }

    /** 
     * Insert a coin into the purse.
     * The coin is only inserted if the purse has space for it
     * and the coin has positive value.  No worthless coins!
     * @param coin is a Coin object to insert into purse
     * @return true if coin inserted, false if can't insert
     */
    public boolean insert( Valuable valuable ) {
        // if the purse is already full then can't insert anything.
        if(this.isFull() || valuable.getValue() <= 0){
            return false;
        }
        else {
            money.add(valuable);
            return true;
        }
    }
    
    /**  
     *  Withdraw the requested amount of money.
     *  Return an array of items withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  Returns amount using any currency.
     *  @param amount is the amount to withdraw
     *  @return array of items objects for money withdrawn,
	 *  or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw( double amount ) {
        return withdraw(new Money(amount, "Baht"));
	}

	Valuable[] withdraw(Valuable amount){
        if(amount.getValue() <= 0 || amount == null)return null;

        List<Valuable> templist = new ArrayList<Valuable>();

        Collections.sort( money, comp );

        double amountNeededToWithdraw = amount.getValue();
        String stringAmount = amount.getCurrency();

        for (int i = money.size()-1 ; i >= 0 ; i--){
            Valuable m = money.get(i);
            // failed. Don't change the contents of the purse.
            if(m.getCurrency().equalsIgnoreCase(stringAmount)){
                if((amountNeededToWithdraw >= m.getValue())){
                    templist.add(m);
                    amountNeededToWithdraw -= m.getValue();
                    money.remove(i);
                }
            }
                if(amountNeededToWithdraw == 0){ break; }
        }

        //This case is used to detect any abnormal withdraws.
        if(amountNeededToWithdraw > 0){
            money.addAll(templist);
            return null;
        }
        // Success.
        // Remove the items you want to withdraw from purse,
        // and return them as an array.
        // Use list.toArray( array[] ) to copy a list into an array.
        // toArray returns a reference to the array itself.
        else{
            Valuable[] arrayVal = new Valuable[templist.size()];
            return templist.toArray(arrayVal);
        }


    }
  
    /** 
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */

    public String toString() {
    	return String.format("%d items with value %.2f",this.count(),this.getBalance());
    }

}

