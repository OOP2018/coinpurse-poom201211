package coinpurse;

public class ThaiMoneyFactory extends MoneyFactory{

    private static MoneyFactory instance = new ThaiMoneyFactory();

    public static MoneyFactory getInstance() {
        return instance;
    }

    public Valuable createMoney(double value){

        double[] coinArray = {1,2,5,10};
        double[] bankArray = {20,50,100,500,1000};

        try{
            for(int i = 0; i < coinArray.length; i++){
                if(value == coinArray[i])return new Coin(coinArray[i],"Baht");
            }
            for(int k = 0; k < bankArray.length; k++){
                if(value == bankArray[k])return new BankNote(bankArray[k],"Baht");
            }
        }catch(IllegalArgumentException ex){
            System.out.println("Values not in given range");
        }
        return null;
    }
}

