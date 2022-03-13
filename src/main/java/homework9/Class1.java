package homework9;

import org.testng.annotations.*;

public class Class1 {
    @org.testng.annotations.DataProvider(name = "test-data-1")
    public Object[][] testData1(){
        return new Object[][] {{"1 2"}};
    }

    @org.testng.annotations.DataProvider(name = "test-data-2")
    public Object[][] testData2(){
        return new Object[][] {{"three four"}, {"true false"}};
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Before Suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass");
    }

    @Test
    public void test_1() {
        System.out.println("Test");
    }


    @Test(dataProvider = "test-data-1")
    public void test_2 (String data) {
        System.out.println("Test");
        System.out.println("Test data: " + data);
    }

    @Test
    public void test_3() {

    }

    @Test(dataProvider = "test-data-2")
    public void test_4 (String data) {
        System.out.println("Test");
        System.out.println("Test data: " + data);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AfterMethod");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("AfterTest");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("AfterSuite");
    }
}
