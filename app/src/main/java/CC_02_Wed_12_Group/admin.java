package CC_02_Wed_12_Group;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class admin extends NewUser{

    public admin() {
        this.exchangeList = loadExchangeList();
        this.transitLogHashMap = loadTransitLogHashMap();
    }


    // Edit 
    public void editDate(String date) {


        if (transitLogHashMap == null) {
            this.transitLogHashMap = new HashMap<String, ArrayList<Rate>>();

        }
        transitLogHashMap.put(date, exchangeList);

        try (FileWriter file = new FileWriter("./src/main/resources/TrasitLogs.json")) {
            Gson gson = new Gson();
            String json = gson.toJson(transitLogHashMap);
            file.write(json);
            file.flush();
            file.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void editExchange(String currencyFrom, String currencyTo, float rate) {

        float newRate = 0;

        if (rate <= 0) {
            System.out.println("Rate can not be less than 0");
            return;
        }

        if (checkIfInArrayList(currencyTo, exchangeList) == false ||
                checkIfInArrayList(currencyFrom, exchangeList) == false) {
            System.out.println("No such currency");
            return;
        }

        if (currencyFrom.equals("USD")) {
            newRate = rate;
            for(Rate i: exchangeList) {
                if (i.getName().equals(currencyTo)) {
                    if (i.getRate() < newRate ) {
                        i.setUpOrDown(2);
                        // Down
                    } else if (i.getRate() > newRate) {
                        i.setUpOrDown(1);
                        // Same rate
                    } else {
                        i.setUpOrDown(0);
                    }
                }
                if (i.getName().equals(currencyTo)) {
                    i.setRate(rate);
                }
            }

        } else {
            for(Rate i: exchangeList) {
                if (i.getName().equals(currencyTo)) {
                    newRate = i.getRate();
                }
            }
            newRate = 1/(newRate*rate);
            for (Rate i: exchangeList) {
                if (i.getName().equals(currencyFrom)) {
                    // Up
                    if (i.getRate() < newRate ) {
                        i.setUpOrDown(2);
                        // Down
                    } else if (i.getRate() > newRate) {
                        i.setUpOrDown(1);
                        // Same rate
                    } else {
                        i.setUpOrDown(0);
                    }
                }
            }

            for(Rate i: exchangeList) {
                if (i.getName().equals(currencyFrom)) {
                    i.setRate(newRate);;
                }
            }
        }


        Gson gson = new Gson();
        String newJson = gson.toJson(exchangeList);
        try (FileWriter file = new FileWriter("./src/main/resources/Exchange.json")) {
            file.write(newJson);
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    // Add
    public void addCurrency(String currency, String date, float rate) {
        if (rate <= 0) {
            System.out.println("Rate can not be less than 0");
            //return ExchangeList;
        }

        // The Currency trying to be added already exists in the database
        // Edit existing currency.
        if (checkIfInArrayList(currency, exchangeList)) {
            System.out.println("Error: Cannot add an existing currency");
            //return ExchangeList;
        }

        //Create new rate object and add to Exchange ArrayList
        Rate newRate = new Rate(currency, rate);
        this.exchangeList.add(newRate);

        Gson gson = new Gson();
        String newJson = gson.toJson(exchangeList);
        try (FileWriter file = new FileWriter("./src/main/resources/Exchange.json")) {
            file.write(newJson);
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        //return ExchangeList;
        // Write current transLog to file
        
    }


    public void adminFuncitonality(int cmd) {
            Scanner sc = new Scanner(System.in);

            // Add currency
            if (cmd == 5) {
                System.out.println("Enter Currency you want to add (exchange rate to USD):");
                String currency = sc.next().toUpperCase();

                if (checkIfInArrayList(currency, exchangeList)) {
                    System.out.println("Cannot add existing currency");
                    return;
                }

                System.out.println("Enter Currency rate:");
                if (sc.hasNextFloat() == false) {
                    System.out.println("Rate must be number");
                    return;
                }
                float newRate = sc.nextFloat();

                System.out.println("Enter the date(like YYYY-MM-DD): ");
                String date = sc.next();
                if (!checkDate(date)) {
                    System.out.println("Please enter the date in correct format");
                    return;
                }

                addCurrency(currency, date, newRate);

                editDate(date);
                for (Rate i : this.exchangeList) {
                    System.out.printf("%s: %.2f\n", i.getName(), i.getRate());
                }
            }
            // Edit
            else {
                System.out.println("Enter Currency you want to change:");
                String currencyFrom = sc.next().toUpperCase();
                if (!checkIfInArrayList(currencyFrom, exchangeList)) {
                    System.out.println("No Such Currency");
                    return;
                }
                System.out.println("Enter Currency you want to change to:");
                String currencyTo = sc.next().toUpperCase();
                if (!checkIfInArrayList(currencyTo, exchangeList)) {
                    System.out.println("No Such Currency");
                    return;
                }
                System.out.println("Enter Currency rate:");
                if (sc.hasNextFloat() == false) {
                    System.out.println("Rate must be number");
                    return;
                }
                float newRate = sc.nextFloat();
                System.out.println("Enter the date(like YYYY-MM-DD): ");
                String date = sc.next();
                if (!checkDate(date)) {
                    System.out.println("Please enter the date in correct format");
                    return;
                }

                editExchange(currencyFrom, currencyTo, newRate);
                editDate(date);
                for (Rate i : this.exchangeList) {
                    System.out.printf("%s: %.2f\n", i.getName(), i.getRate());
                }
            }
        }

}