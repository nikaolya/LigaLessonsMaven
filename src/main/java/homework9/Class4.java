package homework9;

import org.testng.annotations.*;

public class Class4 {
    @BeforeTest
    public void beforeTest() {
        System.out.println("BeforeTest");
    }

    @Test
    public void test_1() {
        System.out.println("Test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }
}
