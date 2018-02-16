package coinpurse;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyFactoryTest {

    @Test
    public void testGetInstance() {
        MoneyFactory factory = MoneyFactory.getInstance();

        //*Default get instance started with assigning factory to ThaiMoneyFactory.
        assertEquals(factory,ThaiMoneyFactory.getInstance());
        //*Should not be equals as the MalaoMoneyFactory is not added as an instance.
        assertNotEquals(factory,MalaiMoneyFactory.getInstance());
    }

    @Test
    public void testCreateMoneyDouble() {
        MoneyFactory factory = MoneyFactory.getInstance();

        Coin oneBahtCoin = (Coin)factory.createMoney(1);
        Coin twoBahtCoin = (Coin) factory.createMoney(2);
        BankNote fiftyBahtNote = (BankNote) factory.createMoney(50);
        BankNote thousandBahtNote = (BankNote) factory.createMoney(1000);
        //*Test null as 4 baht coin doesnt exist.
        Coin fourBahtCoin = (Coin) factory.createMoney(4);

        assertEquals(new Coin(1,"Baht"), oneBahtCoin);
        assertEquals(new Coin(2,"Baht"), twoBahtCoin);
        assertEquals(new BankNote(50,"Baht"), fiftyBahtNote);
        assertEquals(new BankNote(1000,"Baht"), thousandBahtNote);
        //*Test not equals
        assertNotEquals(new Coin(4,"Baht"), fourBahtCoin);
    }

    @Test
    public void testCreateMoneyString() {
        MoneyFactory factory = MoneyFactory.getInstance();

        Coin oneBahtCoin = (Coin)factory.createMoney("1");
        Coin tenBahtCoin = (Coin) factory.createMoney("10");
        BankNote twentyBahtNote = (BankNote) factory.createMoney("20");
        BankNote fivehundredBahtNote = (BankNote) factory.createMoney("500");
        BankNote thousandDoubleBahtNote = (BankNote) factory.createMoney(1000.0);
        //*Testing non-String values
        Coin tenaBahtCoin = (Coin) factory.createMoney("10a");

        assertEquals(new Coin(1,"Baht"),oneBahtCoin);
        assertEquals(new Coin(10,"Baht"),tenBahtCoin);
        assertEquals(new BankNote(20,"Baht"),twentyBahtNote);
        assertEquals(new BankNote(500,"Baht"),fivehundredBahtNote);
        assertEquals(new BankNote(1000,"Baht"),thousandDoubleBahtNote);
        //*Test not equals
        assertEquals(null,tenaBahtCoin);
        assertNotEquals(new Coin(10,"Baht"),tenaBahtCoin);
    }

    @Test
    public void testSetFactory() {
        //*Testing default instance(ThaiMoneyFactory)
        MoneyFactory factory = MoneyFactory.getInstance();

        assertEquals(ThaiMoneyFactory.getInstance(),factory);

        //*Adding MalaiMoneyFactory to the MoneyFactory instance
        MoneyFactory.setFactory(new MalaiMoneyFactory());
        MoneyFactory factoryWithMalai = MoneyFactory.getInstance();

        assertEquals(MalaiMoneyFactory.getInstance().getClass(),factoryWithMalai.getClass());
    }
}