package coinpurse;

public class MalaiMoneyFactory extends MoneyFactory{

    private static MoneyFactory instance = new MalaiMoneyFactory();

    public static MoneyFactory getInstance() {
        return instance;
    }

    public Valuable createMoney(double value){

        double[] coinArray = {0.05,0.10,0.20,0.50};
        double[] bankArray = {1,2,5,10,20,50,100};

        try{
            for(int i = 0; i < coinArray.length; i++){
                if(value == coinArray[i])return new Coin(coinArray[i]*100,"Sen");
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
