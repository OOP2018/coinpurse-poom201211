package coinpurse;

public abstract class MoneyFactory {

    private static MoneyFactory instance;

    public static MoneyFactory getInstance(){
        if(instance == null){
            instance = new ThaiMoneyFactory();
        }
        return instance;
    }

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

    public static void setFactory(MoneyFactory f){
        instance = f;
    }

}
