package coinpurse;

import java.util.ArrayList;

public class ThaiMoneyFactory extends MoneyFactory{

    private static long nextSerialNumber = 100_000_000L;

    public Valuable createMoney(double value){

        double[] coinArray = {1,2,5,10};
        double[] bankArray = {20,50,100,500,1000};


        for(int i = 0; i < coinArray.length; i++){
            if(value == coinArray[i])return new Coin(coinArray[i],"Baht");
        }
        for(int k = 0; k < bankArray.length; k++){
            if(value == bankArray[k]){
                BankNote banknote = new BankNote(bankArray[k],"Baht");
                banknote.setSerialNumber(nextSerialNumber++);
                return banknote;
            }
        }
        
        throw new IllegalArgumentException(String.format("%s is not a valid value.",value));
    }
}

