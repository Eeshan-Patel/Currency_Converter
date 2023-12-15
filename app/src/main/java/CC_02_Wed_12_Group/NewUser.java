package CC_02_Wed_12_Group;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class NewUser implements LoadJason{
    protected static ArrayList<Rate> exchangeList;
    final static String DATE_FORMAT = "yyyy-mm-dd";
    protected static HashMap<String, ArrayList<Rate>> transitLogHashMap = new HashMap<String,ArrayList<Rate>>();

    public NewUser() {
        exchangeList = loadExchangeList();
        transitLogHashMap = loadTransitLogHashMap();
    }

    public void refresh() {
        exchangeList = loadExchangeList();
        transitLogHashMap = loadTransitLogHashMap();
    }


    public boolean checkDate (String date) {
        if (date.length() != 10) {
            return false;
        }
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void viewCurrency(String currencyFrom, String currencyTo, float amount) {
        float converted_amount;
        float current_rate = 0;
        float desired_rate = 0;

        for (Rate i: exchangeList) {
            if (i.getName().equals(currencyTo)) {
                desired_rate = i.getRate();
                i.increaseCount();
            }

            if (i.getName().equals(currencyFrom)) {
                current_rate = i.getRate();
                i.increaseCount();
            }
        }
        System.out.printf("The exchange amount from %s to %s is %.2f\n", currencyFrom, currencyTo,  current_rate/ desired_rate * amount);
    }

    public ArrayList<Float> SummaryOutPut(String dateBefore,
                                          String dateAfter, String currencyFrom, String currencyTo) {
        LocalDate afterDate = LocalDate.parse(dateAfter);
        LocalDate beforeDate = LocalDate.parse(dateBefore);

        if (checkIfInArrayList(currencyFrom, exchangeList) == false && checkIfInArrayList(currencyTo,exchangeList) == false) {
            System.out.println("No such currency");
            return null;
        }

        ArrayList<Float> mathArrayList = new ArrayList<Float>();
        float currentFromNum = 0;
        float currentToNum = 0;
        LocalDate newDate = null;
        for (String i: transitLogHashMap.keySet()) {
            newDate = LocalDate.parse(i);
            if ((newDate.isAfter(beforeDate) && newDate.isBefore(afterDate))) {

                for (Rate y : transitLogHashMap.get(i)) {
                    if (y.getName().equals(currencyFrom)) {
                        currentFromNum = y.getRate();
                    }
                    if (y.getName().equals(currencyTo)) {
                        currentToNum = y.getRate();
                    }
                }
                mathArrayList.add(currentFromNum/currentToNum);
            }
        }
        if (mathArrayList.isEmpty()) {
            return null;
        }
        return mathArrayList;
    }

    public void printSummary(String currencyFrom, String currencyTo, String dateBefore,
                             String dateAfter, ArrayList<Float> mathArrayList) {
        System.out.printf("The %s to %s Between %s and %s are: \n", currencyFrom, currencyTo, dateBefore, dateAfter);
        if (mathArrayList == null) {
            System.out.printf("The no such currency %s to %s Between %s and %s! \n", currencyFrom, currencyTo, dateBefore, dateAfter);
            return;
        }
        mathArrayList.forEach(System.out::println);
        float mean = 0;
        float temp = 0;
        for (float i: mathArrayList) {
            mean += i;
        }
        mean = mean/mathArrayList.size();
        System.out.println("The Mean of the Currency conversion is " + mean);
        float max = Collections.max(mathArrayList);
        float min = Collections.min(mathArrayList);

        for (float i: mathArrayList) {
            double squrDiffToMean = Math.pow(i - mean, 2);
            temp += squrDiffToMean;
        }
        float standardDeviation = temp / mathArrayList.size();
        System.out.println("The Max of the Currency conversion is " + max);
        System.out.println("The Min of the Currency conversion is " + min);
        System.out.println("The Standard Deviation of the Currency conversion is " + standardDeviation);
    }



    public void userFunctionality(int cmd) {
        Scanner sc = new Scanner(System.in);
        if (cmd == 3) {
            table();
            return;
        }

        // CHANGE WORDING FOR PROMPTS
        System.out.println("Enter Currency you want to see:");
        String currencyFrom = sc.next().toUpperCase();

        if (!checkIfInArrayList(currencyFrom, exchangeList)) {
            System.out.println("No Such Currency");
            return;
        }

        System.out.println("Enter Currency you want to see to:");
        String currencyTo = sc.next().toUpperCase();
        if (!checkIfInArrayList(currencyTo, exchangeList)) {
            System.out.println("No Such Currency");
            return;
        }

        if (cmd == 1) {
            System.out.println("Enter Currency amount:");
            if (sc.hasNextFloat() == false) {
                System.out.println("Rate must be number");
                return;
            }
            float amount = sc.nextFloat();

            viewCurrency(currencyFrom,currencyTo,amount);
        }

        // Summary
        else if (cmd == 2) {
            System.out.println("Enter the date from (like YYYY-MM-DD): ");
            String dateFrom = sc.next();
            if (!checkDate(dateFrom)) {
                System.out.println("Please enter the date in correct format" + dateFrom);
                return;
            }
            System.out.println("Enter the date to  (like YYYY-MM-DD): ");
            String dateTo = sc.next();
            if (!checkDate(dateTo)) {
                System.out.println("Please enter the date in correct format");
                return;
            }
            ArrayList<Float> math_list = SummaryOutPut(dateFrom, dateTo,currencyFrom,currencyTo);
            printSummary(currencyFrom, currencyTo, dateFrom, dateTo, math_list);
        } else {
            System.out.println("Please have a valid command");
        }
    }
    public Rate[] orderArray(Rate[] array, int index, Rate currency) {
        for (int i = 0; i < index + 1; i++) {
            if (i == index) {
                array[i] = currency;
            }
            else {
                array[i] = array[i+1];
            }
        }
        return array;
    }


    public Rate[] intializeTable() {
        Rate[] popular_currencies = new Rate[4];

        ArrayList<Rate> currExchangeList = loadExchangeList();

        // Stores the first 4 currencies
        for (int i = 0; i < 4; i++) {
            popular_currencies[i] = currExchangeList.get(i);
        }

        // Sort popular_currencies array by count ascending order
        for (int k = 0; k < 4 - 1; k++) {
            for (int j = 0; j < 4 - k - 1; j++) {
                if (popular_currencies[j].getCount() > popular_currencies[j + 1].getCount()) {
                    // swap popular_currencies[j+1] and popular_currencies[j]
                    Rate temp = popular_currencies[j];
                    popular_currencies[j] = popular_currencies[j + 1];
                    popular_currencies[j + 1] = temp;
                }
            }
        }


        return popular_currencies;
    }



    public void displayTable(Rate[] popular_currencies) {
        String horizontalLine = "_______________________________________________________";
        String horizontalLine2 = "-------------------------------------------------------";
        System.out.println(horizontalLine);
        System.out.printf("|From/To   |%-10s|%-10s|%-10s|%-10s|\n",

                popular_currencies[0].getName(), popular_currencies[1].getName(),
                popular_currencies[2].getName(), popular_currencies[3].getName());
        System.out.println(horizontalLine);
        for (int row = 0; row < 4; row++) {

            for (int col = 0; col < 4; col++) {
                if (col == 0) {
                    System.out.printf("|%-10s|", popular_currencies[row].getName());
                }
                float current_rate = 0;
                float desired_rate = 0;
                // same currency
                if (row == col) {
                    System.out.printf("    -     |");
                    continue;
                }

                // Finding rate
                for (Rate currency: exchangeList) {
                    if (currency.getName().equals(popular_currencies[row].getName())) {
                        desired_rate = currency.getRate();
                        // System.out.println("DESIRED RATE");
                        // System.out.println(popular_currencies[row].getName());
                    }

                    if (currency.getName().equals(popular_currencies[col].getName())) {
                        current_rate = currency.getRate();
                        // System.out.println("cruennt RATE");
                        // System.out.println(popular_currencies[col].getName());
                    }
                    // " 5.07     "
                    // " 7.14 (D) "
                }

                if (col == 3) {
                    System.out.printf(" %-2.2f %s |\n", current_rate / desired_rate, popular_currencies[row].getUpOrDown());
                    System.out.println(horizontalLine2);
                }
                else {
                    System.out.printf(" %-2.2f %s |", current_rate / desired_rate, popular_currencies[row].getUpOrDown());
                }
            }
        }
        System.out.printf("\n%s\n", horizontalLine2);

    }

    public Rate[] sortCurrencyArray(ArrayList<Rate> currExchangeList, Rate[] popular_currencies) {
        // Stores the 4 highest counts
        for (int i = 4; i < currExchangeList.size(); i++) {
            Rate currRate = currExchangeList.get(i);
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (currRate.getCount() > popular_currencies[j].getCount()) {
                    index++;
                }
            }
            popular_currencies = orderArray(popular_currencies, index, currRate);
            i++;
        }
        return popular_currencies;

    }

    public void table() {
        // Intialize rate array of size 4 with first 4 currencies sorted
        Rate[] popular_currencies = new Rate[4];
        popular_currencies = intializeTable();

        ArrayList<Rate> currExchangeList = loadExchangeList();

        // Stores the 4 highest counts
        for (int i = 4; i < currExchangeList.size(); i++) {
            Rate currRate = currExchangeList.get(i);
            int index = 0;
            for (int j = 0; j < 4; j++) {
                if (currRate.getCount() > popular_currencies[j].getCount()) {
                    index++;
                }
            }
            popular_currencies = orderArray(popular_currencies, index, currRate);
            i++;
        }

        // Output table
        displayTable(popular_currencies);
    }
}
