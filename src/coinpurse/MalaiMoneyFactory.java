package coinpurse;

public class MalaiMoneyFactory extends MoneyFactory{
    // Initialize attribute nextSerialNumber for adding next number to serial bank note
    private static long nextSerialNumber = 100_000_000L;
    
    // A method for creating each BankNote and Coin.
    public Valuable createMoney(double value){

        double[] coinArray = {0.05,0.10,0.20,0.50};
        double[] bankArray = {1,2,5,10,20,50,100};


        for(int i = 0; i < coinArray.length; i++){
            if(value == coinArray[i])return new Coin(coinArray[i]*100,"Sen");
        }
        for(int k = 0; k < bankArray.length; k++){
            if(value == bankArray[k]){
                BankNote banknote = new BankNote(bankArray[k],"Ringgit");
                banknote.setSerialNumber(nextSerialNumber++);
                return banknote;
            }
        }
        throw new IllegalArgumentException(String.format("%s is not a valid value.",value));
    }
}
