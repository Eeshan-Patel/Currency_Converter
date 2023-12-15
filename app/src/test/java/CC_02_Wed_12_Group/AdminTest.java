package CC_02_Wed_12_Group;

import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class AdminTest implements LoadJason{

    admin newAdmin;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
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
        @BeforeEach
        public void setUpAdmin() {
            newAdmin = new admin();
        }

        @Test
        public void testAddCurrency() {
            ArrayList<Rate> intialExchange = loadExchangeList();
            newAdmin.addCurrency("GBP", "2022-09-17", (float) 1.2);

            // New currency added to ExchangeList
            ArrayList<Rate> afterExchange = loadExchangeList();

            assertFalse(intialExchange == afterExchange);
        }

        @Test
        public void addCurrencyCommandTest() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String data = "JPY\n 0.007\n 2011-01-01";
            InputStream stdin = System.in;
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            newAdmin.adminFuncitonality(5);
            System.setIn(stdin);
            String expectedOutput  = "Enter Currency you want to add (exchange rate to USD):\n" +
                    "Enter Currency rate:\n" +
                    "Enter the date(like YYYY-MM-DD): \n" +
                    "USD: 1.00\n" +
                    "AUD: 0.68\n" +
                    "SGD: 0.71\n" +
                    "EUR: 1.00\n" +
                    "CNY: 0.14\n" +
                    "GBP: 1.20\n" +
                    "JPY: 0.01\n";
            assertEquals(expectedOutput, outContent.toString());
        }



        @Test
        public void editCurrencyTest() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            newAdmin.editExchange("EUR", "USD", (float) 0.5263158);
            ArrayList<Rate> intialExchange = loadExchangeList();
            for (Rate i: intialExchange) {
                if (i.getName().equals("EUR")) {
                    assertEquals(i.getRate(), (float)1.9);
                }
            }
            String expectedOutput  = "No such currency\n";
            newAdmin.editExchange("USD", "ASSS", (float)1.9);
            assertEquals(expectedOutput, outContent.toString());
        }

        @Test
        public void editCurrencyTest2() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            newAdmin.editExchange("USD", "CNY", 2);
            ArrayList<Rate> intialExchange = loadExchangeList();
            for (Rate i: intialExchange) {
                if (i.getName().equals("CNY")) {
                    assertEquals(i.getRate(), 2);
                }
            }
            newAdmin.editExchange("USD", "CNY", (float)0.14);

        }

        @Test
        public void adminFunctionalityTest() {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            String data = "EUR\n USD\n 1\n 2011-01-01";
            InputStream stdin = System.in;
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            newAdmin.adminFuncitonality(4);
            System.setIn(stdin);
            String expectedOutput  = "Enter Currency you want to change:\n" +
                    "Enter Currency you want to change to:\n" +
                    "Enter Currency rate:\n" +
                    "Enter the date(like YYYY-MM-DD): \n" +
                    "USD: 1.00\n" +
                    "AUD: 0.68\n" +
                    "SGD: 0.71\n" +
                    "EUR: 1.00\n" +
                    "CNY: 0.14\n";
            assertEquals(expectedOutput, outContent.toString());
        }


}


