package CC_02_Wed_12_Group;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UserTest implements LoadJason{


    @Test
    public void testIntializationTable() {
        NewUser newUser = new NewUser();
        ArrayList<Rate> intialExchange = loadExchangeList();
        Rate[] popular_currencies = new Rate[4];
        popular_currencies =  newUser.intializeTable();

        // Intialize an array of first 4 currencies in exchange
        for (int i = 0; i < 4; i++) {
            assertEquals(popular_currencies[i].getName(), intialExchange.get(i).getName());
        }
    }

    @Test
    public void testTable() {
        NewUser newUser = new NewUser();
        ArrayList<Rate> intialExchange = loadExchangeList();
        Rate[] popular_currencies = new Rate[4];
        popular_currencies =  newUser.intializeTable();

        // Find 4 most popular currencies and sort them
        Rate[] sortedCurrencies = newUser.sortCurrencyArray(intialExchange, popular_currencies);

        //change back
        assertTrue(popular_currencies == sortedCurrencies);

        newUser.table();
    }
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;



    @Before
    public void setStreams() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }
    @After
    public void restoreInitialStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void checkDateTest() {
        NewUser a = new NewUser();
        assertTrue(a.checkDate("2011-01-01"));
        assertFalse(a.checkDate("01012022"));
        assertFalse(a.checkDate("01-01-2022"));
    }


    @Test
    public void viewCurrencyTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NewUser a = new NewUser();
        a.viewCurrency("AUD", "USD", 1);

        String expectedOutput  = "The exchange amount from AUD to USD is 0.68\n"; // Notice the \n for new line.

        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    public void SummaryOutPutTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NewUser a = new NewUser();
        ArrayList<Float> temp = a.SummaryOutPut("2011-01-01", "2011-01-01", "AUD", "USD");
        assertNull(temp);
        temp = a.SummaryOutPut("2011-01-01", "2011-01-01", "ssss", "sssa");
        String expectedOutput  = "No such currency\n"; // Notice the \n for new line.

        assertEquals(expectedOutput, outContent.toString());
    }


    @Test
    public void userFunctionalityTest1() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NewUser a = new NewUser();

        String data = "AUD\n USD\n 1\n";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        a.userFunctionality(1);
        System.setIn(stdin);

        String expectedOutput  = "Enter Currency you want to see:\n" +
                "Enter Currency you want to see to:\n" +
                "Enter Currency amount:\n" +
                "The exchange amount from AUD to USD is 0.68\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void userFunctionalityTest2() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NewUser a = new NewUser();

        String data = "AUD\n USD\n 2011-01-01\n 2011-01-01";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        a.userFunctionality(2);
        System.setIn(stdin);
        String expectedOutput  = "Enter Currency you want to see:\n" +
                "Enter Currency you want to see to:\n" +
                "Enter the date from (like YYYY-MM-DD): \n" +
                "Enter the date to  (like YYYY-MM-DD): \n" +
                "The AUD to USD Between 2011-01-01 and 2011-01-01 are: \n" +
                "The no such currency AUD to USD Between 2011-01-01 and 2011-01-01! \n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void editChangeTest() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        admin a = new admin();
        a.editDate("2011-01-01");
        a.editDate("2011-01-02");
        NewUser b = new NewUser();

        String data = "AUD\n USD\n 2010-12-30\n 2011-01-05";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        b.userFunctionality(2);
        System.setIn(stdin);
        String expectedOutput  = "Enter Currency you want to see:\n" +
                "Enter Currency you want to see to:\n" +
                "Enter the date from (like YYYY-MM-DD): \n" +
                "Enter the date to  (like YYYY-MM-DD): \n" +
                "The AUD to USD Between 2010-12-30 and 2011-01-05 are: \n" +
                "0.68\n" +
                "0.68\n" +
                "The Mean of the Currency conversion is 0.68\n" +
                "The Max of the Currency conversion is 0.68\n" +
                "The Min of the Currency conversion is 0.68\n" +
                "The Standard Deviation of the Currency conversion is 0.0\n";

        assertEquals(expectedOutput, outContent.toString());
    }





}
