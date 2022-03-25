package homework11;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;


public class Tests {


    @Test
    public void test_1(){
        Selenide.open("https://www.mvideo.ru/stiralnye-i-sushilnye-mashiny-2427/stiralnye-mashiny-89/f/category=uzkie-stiralnye-mashiny-2446?from=under_search");
        Steps steps = new Steps();
        steps.findElement("Стиральная машина узкая Indesit EWUC 4105 CIS");
    }
}
