package coinpurse.strategy;

import coinpurse.BankNote;
import coinpurse.Coin;
import coinpurse.Valuable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the Purse using JUnit.
 * This is a JUnit 4 test suite.
 *
 * @author  Thanapoom Rattanathumawat
 */
public class WithdrawTest {
    private WithdrawStrategy strategy;

    private List<Valuable> list;

    @Before
    public void setUp(){
        strategy = new GreedyWithdraw();
        list = new ArrayList<>();
    }

    @Test
    public void testWithdrawEverything(){
        list.clear();
        list.add(new Coin(1.0, "Baht"));
        list.add(new Coin(2.0, "Baht"));
        list.add(new Coin(5.0, "Baht"));
        list.add(new Coin(10.0, "Baht"));
        Assert.assertEquals(new Coin(1,"Baht"),strategy.withdraw(new Coin(1, "Baht"),list).get(0));
        Assert.assertEquals(new Coin(2,"Baht"),strategy.withdraw(new Coin(2, "Baht"),list).get(0));
        Assert.assertEquals(new Coin(5,"Baht"),strategy.withdraw(new Coin(5, "Baht"),list).get(0));
        Assert.assertEquals(new Coin(10,"Baht"),strategy.withdraw(new Coin(10, "Baht"),list).get(0));
    }

    @Test
    public void testWithdrawEquality(){
        list.clear();
        list.add(new Coin(1.0, "Baht"));
        list.add(new Coin(2.0, "Baht"));
        list.add(new BankNote(20.0, "Baht"));
        list.add(new BankNote(50.0, "Baht"));
        Assert.assertNotEquals(new Coin(5,"Baht"),strategy.withdraw(new Coin(1,"Baht"),list).get(0));
        Assert.assertNotEquals(new Coin(10,"Baht"),strategy.withdraw(new Coin(2,"Baht"),list).get(0));
        Assert.assertNotEquals(new Coin(1,"Baht"),strategy.withdraw(new Coin(20,"Baht"),list).get(0));
        Assert.assertNotEquals(new Coin(2,"Baht"),strategy.withdraw(new Coin(50,"Baht"),list).get(0));
    }

    @Test
    public void testWithdrawZero(){
        list.clear();
        list.add(new Coin(0, "Baht"));
        Assert.assertEquals(null,strategy.withdraw(new Coin(0, "Baht"),list));
    }

    @Test
    public void testImpossibleWithdraw(){
        list.clear();
        list.add(new Coin(5.0, "Baht"));
        list.add(new Coin(2.0, "Baht"));
        Assert.assertEquals(null,strategy.withdraw(new Coin(10,"Baht"),list));
    }

    @Test
    public void testWithdrawDifferentCurrency(){
        list.clear();
        list.add(new Coin(10.0, "Baht"));
        list.add(new Coin(2.0, "Baht"));
        list.add(new Coin(10.0, "Sen"));
        list.add(new Coin(2.0, "Sen"));

        Assert.assertNotEquals(new Coin(10,"Sen"),strategy.withdraw(new Coin(10.0, "Baht"),list));
        Assert.assertNotEquals(new Coin(2,"Sen"),strategy.withdraw(new Coin(2.0, "Baht"),list));
    }

    @Test
    public void testWithdrawSpecificCurrecy(){
        list.clear();
        list.add(new BankNote(20.0, "Baht"));
        list.add(new BankNote(50.0, "Baht"));

        List<Valuable> difList = new ArrayList<>();
        difList.add(new BankNote(20.0, "Baht"));
        difList.add(new BankNote(50.0, "Baht"));

        Assert.assertEquals(difList,list);

        strategy.withdraw(list.get(0),list);
        strategy.withdraw(difList.get(0),difList);
        Assert.assertEquals(strategy.withdraw(new BankNote(20.0, "Baht"),difList),strategy.withdraw(new BankNote(20.0, "Baht"),list));
    }


}
