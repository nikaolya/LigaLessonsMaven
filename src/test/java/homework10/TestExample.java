package homework10;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class TestExample {

    @Before
    public void setUp(){
        Storage.addObject("apple", 3);
        Storage.addObject("pear", 7);
    }
    @Test
    public void simpleTest() {
        Storage.removeObject("pear");
        assertFalse(Storage.isInStock("pear"));
    }
}