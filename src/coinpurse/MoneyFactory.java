package coinpurse;

/**
 * Super class MoneyFactory for creating different banknotes and coins
 */
public abstract class MoneyFactory {

    // A null attribute for assigning each sub-class instance to
    private static MoneyFactory instance;


    /**
     * Method for getting instance of other sub-classes and setting a default instance if null
     * @return instance
     */
    public static MoneyFactory getInstance(){
        if(instance == null){
            instance = new ThaiMoneyFactory();
        }
        return instance;
    }

    /**
     * Abstract method for other sub-classes to initialize
     * @param value
     * @return createMoney(variable)
     */
    public abstract Valuable createMoney(double value);

    public Valuable createMoney(String value){
        double variable = 0;
        try{
            variable = Double.parseDouble(value);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException(String.format("%s is not a valid number.",value));
        }
        return createMoney(variable);
    }

    /**
     * // Method for setting the state of the MoneyFactory class
     * @param f
     */
    public static void setFactory(MoneyFactory f){
        instance = f;
    }

}
