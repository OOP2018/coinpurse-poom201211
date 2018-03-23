package coinpurse.strategy;

import coinpurse.Valuable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyWithdraw implements WithdrawStrategy{
    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        if(amount.getValue() <= 0 || amount == null)return null;

        List<Valuable> templist = new ArrayList<Valuable>();

        Collections.sort( money);
        Collections.reverse( money);

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
        return templist;
    }

}
