package homework9;

import org.testng.annotations.*;

import java.lang.reflect.Method;

public class Class2 {
    @org.testng.annotations.DataProvider(name = "test-data-1")
    public Object[][] testData1(){
        return new Object[][] {{"1 2"}};
    }

    @org.testng.annotations.DataProvider(name = "test-data-2")
    public Object[][] testData2(){
        return new Object[][] {{"three four"}, {"true false"}};
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("BeforeClass");
    }

    @Test
    public void test_1() {
        System.out.println("Test");
    }

    @Test
    public void test_2() {
        System.out.println("Test");
    }

    @Test(dataProvider = "test-data-1")
    public void test_3 (String data) {
        System.out.println("Test");
        System.out.println("Test data: " + data);
    }

    @Test(dataProvider = "test-data-2")
    public void test_4 (String data) {
        System.out.println("Test");
        System.out.println("Test data: " + data);
    }

    @AfterMethod
    public void afterMethod(Method method) {
        if (method.getName().equals("test_3")){
            System.out.println("AfterMethod");
        }
    }

    @AfterClass
    public void afterClass() {
        System.out.println("AfterClass");
    }
}
