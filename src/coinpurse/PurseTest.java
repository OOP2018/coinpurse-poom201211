package coinpurse;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.awt.SunHints;

/**
 * Test the Purse using JUnit.
 * This is a JUnit 4 test suite.
 *
 * IDEs (Eclipse, Netbeans, IntelliJ, BlueJ) include JUnit 4,
 * but you have to tell the IDE to add it to your project as a "Library".
 * To run these tests, right click on this file (in Project panel)
 * and choose Run As -> JUnit test
 *
 * @author  Resident Evil
 * @version 2018.01.19
 */
public class PurseTest {
	/** tolerance for comparing two double values */
	private static final double TOL = 1.0E-6;
	private static final String CURRENCY = "BTC";

    /**
     * Sets up the test fixture.
     * Called before every test method.
     */
    @Before
    public void setUp() {
    	// nothing to initialize
    }

    /** Make a coin with both value and currency */
    private Valuable makeCurrencyCoin(double value,String currency) { return new Coin(value,CURRENCY);}

	/** Make a coin with both value and currency */
	private Valuable makeCurrencyBank(double value,String currency) { return new BankNote(value,CURRENCY);}

    /** Make a coin with the default currency. To save typing "new Coin(...)" */
    private Valuable makeCoin(double value) { return new Coin(value,CURRENCY);}

	/** Make a banknote with the deault currency.*/
	private Valuable makeBankNote(double value) {
		return new BankNote(value,CURRENCY);
	}

    /** Easy test that the Purse constructor is working. */
    @Test
    public void testConstructor()
    {
        Purse purse = new Purse(3);
        assertEquals(3, purse.getCapacity());
        assertEquals(false, purse.isFull());
        assertEquals(0, purse.count());
    }



    /** Insert some coins. Easy test. */
    @Test
    public void testInsert()
    {
        Purse purse = new Purse(3);
        Valuable valuable1 = makeCoin(5);
        Valuable valuable2 = makeCoin(10);
        Valuable valuable3 = makeCoin(1);
        assertTrue( purse.insert(valuable1));
        assertTrue( purse.insert(valuable3));
        assertTrue( purse.insert(valuable2));
        assertEquals( 3, purse.count() );
        // purse is full so insert should fail
        assertFalse( purse.insert(makeCoin(1)) );
    }

    //** Test most methods in this test class. */
	@Test
	public void testValuable() {
		Purse purse1 = new Purse(5);
		Coin coin1 = (Coin) makeCoin(5);
		Coin coin2 = (Coin) makeCoin(10);
		BankNote note1 = (BankNote) makeBankNote(20);
		BankNote note2 = (BankNote) makeBankNote(50);
		BankNote note3 = (BankNote) makeBankNote(100);
		//test equals
		assertTrue(coin2.equals(coin2));
		assertFalse(note1.equals(note2));
		assertFalse(note2.equals(note3));
		assertFalse(note1.equals(coin1));
		//test insert
		assertTrue( purse1.insert(note1));
		assertTrue( purse1.insert(note2));
		assertTrue( purse1.insert(note3));
		assertTrue( purse1.insert(coin1));
		assertTrue( purse1.insert(coin2));
		//count
		assertEquals(5, purse1.count());
		//test get balance
		assertEquals(185, purse1.getBalance(), TOL);
		//test withdraw
		purse1.withdraw(20);
		assertEquals(165, purse1.getBalance(),TOL);
		purse1.withdraw(20);
		assertEquals(165, purse1.getBalance(), TOL);
	}

	@Test(timeout=100)
	public void testWithdrawSpecificCurrecy(){
		Purse purse2 = new Purse(3);
		Money note50 = new Money(50,"Baht");
		Money note20 = new Money(20,"Yen");
		Money note100 = new Money(100,"Baht");
		//test insert
		assertTrue( purse2.insert(note50));
		assertTrue( purse2.insert(note20));
		assertTrue( purse2.insert(note100));
		//test get balance
		assertEquals(170, purse2.getBalance(), TOL);
		//test currency withdraw
		purse2.withdraw(note50);
		assertEquals(120, purse2.getBalance(),TOL);
		purse2.withdraw(note20);
		//Cannot withdraw because currency is in Yen.
		assertEquals(120, purse2.getBalance(), TOL);
		//External test.
		purse2.insert(note50);
		purse2.withdraw(new Money(150, "Baht"));
		assertEquals(20, purse2.getBalance(), TOL);
	}


	/** Insert should reject coin with no value. */
    @Test
    public void testInsertNoValue()
    {
        Purse purse = new Purse(3);
        Valuable fakeCoin = new BankNote(0, CURRENCY);
        assertFalse( purse.insert(fakeCoin) );
    }


    @Test(timeout=1000)
    public void testIsFull()
    {   // borderline case (capacity 1)
        Purse purse = new Purse(1);
        assertFalse( purse.isFull() );
        purse.insert( makeCoin(1) );
        assertTrue( purse.isFull() );
        // real test
        int capacity = 4;
        purse = new Purse(capacity);
        for(int k=1; k<=capacity; k++) {
            assertFalse(purse.isFull());
            purse.insert( makeCoin(k) );
        }
        // should be full now
        assertTrue( purse.isFull() );
        assertFalse( purse.insert( makeCoin(5) ) );
    }

	/** Should be able to insert same coin many times,
	 *  since spec doesn't say anything about this.
	 */
	@Test(timeout=1000)
	public void testInsertSameCoin()
	{
		int capacity = 5;
		double value = 10.0;
		Purse purse = new Purse(capacity);
		Valuable valuable = new BankNote(value, "THB");
		assertTrue( purse.insert(valuable) );
		assertTrue( purse.insert(valuable) ); // should be allowed
		assertTrue( purse.insert(valuable) ); // should be allowed
		assertTrue( purse.insert(valuable) ); // should be allowed
		assertTrue( purse.insert(valuable) ); // should be allowed
		assertEquals( purse.getBalance(), 5*value, TOL);
	}

	/** Add one coin and remove it. */
	@Test(timeout=1000)
	public void testEasyWithdraw() {
		Purse purse = new Purse(10);
		double [] values = {1, 20, 0.5, 10}; // values of coins we will insert

		for(double value : values) {
			Valuable valuable = makeCoin(value);
			assertTrue(purse.insert(valuable));
			assertEquals(value,  purse.getBalance(), TOL);
			Valuable [] result = purse.withdraw(value);
			assertTrue( result != null );
			assertEquals( 1, result.length );
			assertSame(  valuable, result[0] ); // should be same object
			assertEquals( 0, purse.getBalance(), TOL );
		}
	}


	/** Add 4 coins and then withdraw in pairs, but not in same order. */
	@Test(timeout=1000)
	public void testMultiWithdraw() {
		Purse purse = new Purse(10);
		Valuable[] coins = { makeCoin(5.0), makeCoin(10.0), makeCoin(1.0), makeCoin(5.0) };
		// insert them all
		for(Valuable coin: coins) assertTrue( purse.insert(coin) );

		double amount1 = coins[1].getValue() + coins[3].getValue();
		double amount2 = coins[0].getValue() + coins[2].getValue();
		assertEquals(amount1+amount2, purse.getBalance(), TOL );

		Valuable [] wd1 = purse.withdraw(amount1);
		assertEquals(amount1, sum(wd1), TOL );


		assertEquals(amount2, purse.getBalance(), TOL );
		Valuable [] wd2 = purse.withdraw(amount2);

		// should be empty now
		assertEquals(0, purse.getBalance(), TOL );
	}


	/** Withdraw full amount in purse, using varying numbers of objects. */
	@Test(timeout=1000)
	public void testWithdrawEverything() {
		Purse purse = new Purse(10);
		// Coins we want to insert and then withdraw.
		// Use values such that greedy will succeed, but not monotonic
		List<Valuable> coins = Arrays.asList(
				makeCoin(1.0), makeCoin(0.5), makeCoin(10.0), makeCoin(0.25), makeCoin(5.0)
				);
		// num = number of coins to insert and then withdraw
		for(int num=1; num <= coins.size(); num++) {
			double amount = 0.0;
			List<Valuable> subList = coins.subList(0, num);
			for(Valuable c: subList) {
				purse.insert(c);
				amount += c.getValue();
			}
			// balance should be exactly what we just inserted
			assertEquals( amount, purse.getBalance(), TOL);
			// can we withdraw it all?
			Valuable[] result = purse.withdraw(amount);
			String errmsg = String.format("couldn't withdraw %.2f but purse has %s",
					amount, Arrays.toString(subList.toArray()) );
			assertNotNull( errmsg, result );
			// is the amount correct?
			assertEquals("Withdraw wrong amount", amount, sum(result), TOL);
			// should not be anything left in the purse
			assertEquals( 0.0, purse.getBalance(), TOL);
		}
	}


	@Test(timeout=1000)
	public void testImpossibleWithdraw() {
		Purse purse = new Purse(10);
		assertNull( purse.withdraw(1) );
		purse.insert( makeCoin(20) );
		assertNull( purse.withdraw(1) );
		assertNull( purse.withdraw(19) );
		assertNull( purse.withdraw(21) );
		purse.insert( makeCoin(20) ); // now it has 20 + 20
		assertNull( purse.withdraw(30) );
	}

	/**
	 * Sum the value of some coins.
	 * @param coins array of coins
	 * @return sum of values of the coins
	 */
	private double sum(Valuable[] valuable)  {
		if (valuable == null) return 0.0;
		double sum = 0;
		for(Valuable c: valuable) if (c != null) sum += c.getValue();
		return sum;
	}
}


