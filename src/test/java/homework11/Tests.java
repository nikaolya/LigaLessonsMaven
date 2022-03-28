package homework11;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static homework11.SelenideConfiguration.setSelenideConfiguration;


public class Tests {

    //MvideoPage mvideoPage = Selenide.page(new MvideoPage());

    @BeforeMethod
    public void beforeMethod(){
        setSelenideConfiguration();
    }


    // Поиск товара по названию
    @Test
    public void test_1(){
        Selenide.open("https://www.mvideo.ru/smartfony-i-svyaz-10/smartfony-205?from=no-search-results");
        Steps steps = new Steps();
        steps.setGridView();
        steps.findElement("Смартфон realme 8i 4+64GB Space Black (RMX3151)");
    }

    // Установка check box
    @Test
    public void test_2(){
        Selenide.open("https://www.mvideo.ru/televizory-i-cifrovoe-tv-1/televizory-65");
        Steps steps = new Steps();
        steps.setGridView();
        steps.checkCheckBox("Операционная система", "Android");
    }

    // Снятие check box
    @Test
    public void test_3(){
        Selenide.open("https://www.mvideo.ru/televizory-i-cifrovoe-tv-1/televizory-65/f/razreshenie-ekrana=8k-7680h4320");
        Steps steps = new Steps();
        steps.setGridView();
        steps.uncheckCheckBox("Разрешение экрана", "8К (7680х4320)");
    }

    // Переключение toggle button
    @Test
    public void test_4(){
        Selenide.open("https://www.mvideo.ru/smartfony-i-svyaz-10/smartfony-205?from=under_search");
        Steps steps = new Steps();
        steps.setGridView();
        steps.setToggleButton("Bluetooth");
    }

/*    // Чтобы после каждого теста закрывался браузер
    @AfterMethod
    public void afterMethod(){
        WebDriverRunner.closeWebDriver();
    }*/

}
