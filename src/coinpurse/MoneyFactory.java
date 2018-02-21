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
        }catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        return createMoney(variable);
    }

    public static void setFactory(MoneyFactory f){
        instance = f;
    }

}
