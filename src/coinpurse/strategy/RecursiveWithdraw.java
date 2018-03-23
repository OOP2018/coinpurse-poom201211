package coinpurse.strategy;

import coinpurse.Valuable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import coinpurse.Money;

public class RecursiveWithdraw implements WithdrawStrategy{

    private List<Valuable> list = new ArrayList<>();

    @Override
    public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
        if(amount.getValue() <= 0){
            return null;
        }
        if(withdrawHelper(0,money,amount))return list;
        return null;
    }

    public boolean withdrawHelper(int start, List<Valuable> money, Valuable amount) {
        if(start >= money.size())return amount.getValue() == 0;
        if(money.get(start).getCurrency().equals(amount.getCurrency())){
            if(withdrawHelper(start + 1, money,new Money(amount.getValue() - money.get(start).getValue(),money.get(start).getCurrency()))){
                list.add(money.get(start));
                return true;
            }
        }
        if(withdrawHelper(start + 1,money,amount)){
            return true;
        }
        return false;
    }
}
