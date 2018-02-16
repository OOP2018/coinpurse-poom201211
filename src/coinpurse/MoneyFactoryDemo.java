package coinpurse;

public class MoneyFactoryDemo {
    public static void main(String[] args) {
        /**
         * Default currency set to Thai-Baht.
         */
        MoneyFactory factory = MoneyFactory.getInstance();

        Valuable m1 = factory.createMoney(5);
        System.out.println(m1.toString());

        Valuable m2 = factory.createMoney("1000.0");
        System.out.println(m2.toString());

        /**
         * Creating new MoneyFactory to use methods from the
         * MalaiMoneyFactory class.
         */
        MoneyFactory.setFactory(new MalaiMoneyFactory());
        MoneyFactory factoryM = MoneyFactory.getInstance();

        Valuable m3 = factoryM.createMoney( 5 );
        System.out.println(m3.toString());

        Valuable m4 = factoryM.createMoney( 0.05 );
        System.out.println(m4.toString());
    }


}
