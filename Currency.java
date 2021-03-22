package sample;
import java.util.ArrayList;
import java.util.Arrays;

public class Currency {

    private final double USD=1.00;

    //STORES CURRENCIES AS STRINGS
    private final ArrayList<String> currencies=new ArrayList<>(Arrays.asList("USD",
            "EUR","JPY","GBP","AUD","CAD","CHF","CNY","SEK","NZD","RUB"));

    //STORES CURRENCIES VALUES CONVERTED TO A DOLLAR
    private final ArrayList<Double> values=new ArrayList<>(Arrays.asList(1.000,
            1.21,0.0093,1.39,0.78,0.79,1.09,0.15,0.11,0.66,0.014));

    //CONVERTS A GIVEN AMOUNT FROM ONE CURRENCY TO GIVEN AMOUNT IN OTHER
    public double convert(double amount ,String valueFrom,String valueTo) {
        int valueFromIndex = 0;
        int valueToIndex = 0;
        for (int i = 0; i < currencies.size(); i++) {
            if (currencies.get(i).equals(valueFrom)) {
                valueFromIndex = i;
            }
            if (currencies.get(i).equals(valueTo)) {
                valueToIndex = i;
            }
        }

        double currencyFrom = values.get(valueFromIndex);
        double currencyTo = values.get(valueToIndex);

        //converting to dollars first
        if (currencyFrom >= USD) {
            amount*=currencyFrom;
        } else if (currencyFrom < USD) {
            amount *= currencyFrom;
        }

        amount/=currencyTo;

        return amount;
    }

    public ArrayList<String> getCurrencies() {
        return currencies;
    }

}
