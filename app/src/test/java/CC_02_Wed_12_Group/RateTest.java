package CC_02_Wed_12_Group;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//As AssertEquals() doesn't compare two floats, a value of 0.1f is added as delta
public class RateTest {

    @Test
    public void RateTestConstructor1(){
        Rate r = new Rate("Australia", 1.00F);
        assertEquals("Australia",r.getName());
        assertEquals(1.00,r.getRate(),0.1f);
    }

    @Test
    public void RateTestConstructor2(){
        Rate r = new Rate("Singapore",0.95F);
        assertEquals("Singapore",r.getName());
        assertEquals(0.95,r.getRate(),0.1f);
        //assertEquals(0,r.getUpOrDown());
        assertEquals(0,r.getCount());
    }

    @Test
    public void RateTestConstructorNegativeRate3(){
        Rate r = new Rate("Canada",-0.78F);
        assertEquals("Canada",r.getName());
        assertEquals(-0.78,r.getRate(),0.1f);
        r.increaseCount();
        assertEquals(1,r.getCount());
    }

    @Test
    public void RateTestConstructorEmptyName4(){
        Rate r = new Rate("",0.22F);
        assertEquals("",r.getName());
        assertEquals(0.22,r.getRate(),0.1f);
        //assertEquals(0,r.getUpOrDown());
        assertEquals(0,r.getCount());
    }

    @Test
    public void RateTestSetRate1(){
        Rate r = new Rate("Australia",1.00F);
        assertEquals("Australia",r.getName());
        assertEquals(1.00,r.getRate(),0.1f);
        r.setRate(1.50F);
        r.increaseCount();
        r.increaseCount();
        assertEquals(1.50,r.getRate(),0.1f);
        r.increaseCount();
        assertEquals(3,r.getCount());
    }

    @Test
    public void RateTestNegativeSetRate2(){
        Rate r = new Rate("USA",1.80F);
        assertEquals("USA",r.getName());
        assertEquals(1.80,r.getRate(),0.1f);
        //assertEquals(0,r.getUpOrDown());
        r.setRate(-1.99F);
        assertEquals(-1.99,r.getRate(),0.1f);
        assertEquals(0,r.getCount());
    }

    @Test
    public void RateTestSetUpOrDown1(){
        Rate r = new Rate("China",0.40F);
        assertEquals("China",r.getName());
        //assertEquals(0,r.getUpOrDown());
        r.increaseCount();
        assertEquals(0.40,r.getRate(),0.1f);
        r.setUpOrDown(2);
        r.increaseCount();
        //assertEquals(2,r.getUpOrDown());
        r.increaseCount();
        r.increaseCount();
        assertEquals(4,r.getCount());
    }

    @Test
    public void RateTestNegativeSetUpOrDown2(){
        Rate r = new Rate("Canada",1.10F);
        assertEquals("Canada",r.getName());
        //assertEquals(0,r.getUpOrDown());
        assertEquals(1.10,r.getRate(),0.1f);
        r.setUpOrDown(-5);
        //assertEquals(-5,r.getUpOrDown());
        assertEquals(0,r.getCount());
    }

    @Test
    public void RateTestAllFunctions(){
        Rate r = new Rate("India",0.93F);
        assertEquals("India",r.getName());
        //assertEquals(0,r.getUpOrDown());
        assertEquals(0.93,r.getRate(),0.1f);
        r.setRate(0.99F);
        assertEquals(0,r.getCount());
        r.increaseCount();
        r.increaseCount();
        r.setUpOrDown(1);
        //assertEquals(1,r.getUpOrDown());
        assertEquals(0.99,r.getRate(),0.1f);
        assertEquals(2,r.getCount());
    }
}