package final_task;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static final_task.SelenideConfiguration.setSelenideConfiguration;


public class Tests {

    @BeforeSuite
    public void beforeSuite(){
        setSelenideConfiguration();
    }

    @Test
    public void test_1(){

        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkIfTabHasCorrectStatus("Статус заказа", ElementStatus.ACTIVE);
        steps.checkIfTabHasCorrectStatus("Войти", ElementStatus.ACTIVE);
        steps.checkIfTabHasCorrectStatus("Сравнение", ElementStatus.DISABLED);
        steps.checkIfTabHasCorrectStatus("Избранное", ElementStatus.DISABLED);
        steps.checkIfTabHasCorrectStatus("Корзина", ElementStatus.DISABLED);
        steps.softAssert.assertAll("Some tests failed");
    }

    @AfterMethod
    public void afterMethod(){
        WebDriverRunner.closeWebDriver();
    }

}
