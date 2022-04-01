package final_task;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import final_task.enums.Button;
import final_task.enums.ElementStatus;
import final_task.enums.Parameter;
import final_task.enums.Section;
import final_task.steps.Steps;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static final_task.SelenideConfiguration.setSelenideConfiguration;


public class Tests {

    @BeforeSuite
    public void beforeSuite(){
        setSelenideConfiguration();
    }

    @Test(testName = "1.\tПроверка шапки главной страницы mvideo")
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

    @Test(testName = "2.\tПроверка активации кнопки корзины")
    public void test_2(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkDayProductTitle("Товары дня");
        steps.clickAddToCartButton();
        steps.checkIfTabHasCorrectStatus("Корзина", ElementStatus.ACTIVE);
        steps.getNumberOfProductsInCartFromBubble(1);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "3.\tПереход в корзину")
    public void test_3(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkDayProductTitle("Товары дня");

        List<String> addedProductsTitles = new ArrayList<>();
        List<String> addedProductsPrices = new ArrayList<>();
        addedProductsTitles.add(steps.saveProductParameter(Section.DAYPRODUCT, Parameter.TITLE));
        addedProductsPrices.add(steps.saveProductParameter(Section.DAYPRODUCT, Parameter.PRICE));
        steps.clickAddToCartButton();
        steps.pressCartButton("/cart");
        steps.checkCartPageTitle("Моя корзина");
        steps.checkProductsTitlesInCart(addedProductsTitles);
        steps.checkThatCheckoutButtonIsDisplayed("Перейти к оформлению");
        steps.checkNumberOfProductsInCart("В корзине 1 товар");
        steps.checkTotalPrice(addedProductsPrices);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "4.\tДобавление в корзину два товара")
    public void test_4(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkThatMostlySeenIsDisplayed();
        List<String> addedProductsTitles = new ArrayList<>();
        List<String> addedProductsPrices = new ArrayList<>();
        for (int index = 1; index <= 2; index++) {
            addedProductsTitles.add(steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.TITLE, index));
            addedProductsPrices.add(steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.PRICE, index));
            steps.addToCartFromMostlySeen(index);
        }

        steps.pressCartButton("/cart");
        steps.checkProductsTitlesInCart(addedProductsTitles);
        steps.checkNumberOfProductsInCart("В корзине 2 товара");
        steps.checkTotalPrice(addedProductsPrices);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "5.\tПоиск товаров")
    public void test_5(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkThatInputFieldIdDisplayed();
        steps.searchText("apple", "/product-list-page");
        steps.checkProductsTitlesOnPage("apple", "");

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "6.\tСортировка товаров в листинге")
    public void test_6(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkThatInputFieldIdDisplayed();
        steps.searchText("apple", "/product-list-page");
        steps.checkProductsTitlesOnPage("apple", "");
        steps.checkThatSortDropdownIsDisplayed("Сначала популярные");
        steps.setSortingType("Сначала дороже");
        steps.checkThatSortDropdownIsDisplayed("Сначала дороже");
        steps.checkProductsTitlesOnPage("apple", "Сначала дороже");


        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "7.\tПроверка модального окна авторизации клиента")
    public void test_7(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkIfTabHasCorrectStatus("Войти", ElementStatus.ACTIVE);
        steps.pressLogInButton();
        steps.checkThatCloseButtonIsDisplayed();
        steps.checkModalWindowTitle("Вход или регистрация");
        steps.checkPlaceholderText("Телефон");
        steps.checkContinueButtonStatus(ElementStatus.DISABLED, "Продолжить");
        steps.checkLinkText("Для юридических лиц");

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "8.\tПроверка добавления товаров в список сравнения")
    public void test_8(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();
        steps.checkThatInputFieldIdDisplayed();
        steps.searchText("apple", "/product-list-page");

        List<String> addedProductsTitles = new ArrayList<>();
        for (int index = 1; index <= 3; index++) {
            addedProductsTitles.add(steps.saveProductParameter(Section.LISTINGPAGE, Parameter.TITLE, 1,index));
            steps.pressButton(Button.COMPARE,1,index);
        }
        steps.checkIfTabHasCorrectStatus("Сравнение",ElementStatus.ACTIVE);
        steps.pressCompareButton("/product-comparison");
        steps.checkComparePageTitle("Сравнение товаров");
        steps.checkProductsTitlesInComparedPage(addedProductsTitles);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "9.\tПроверка добавления товара в список избранного")
    public void test_9(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();
        steps.checkThatInputFieldIdDisplayed();
        steps.searchText("apple", "/product-list-page");

        List<String> addedProductsTitles = new ArrayList<>();
        for (int index = 1; index <= 3; index++) {
            addedProductsTitles.add(steps.saveProductParameter(Section.LISTINGPAGE, Parameter.TITLE, 1,index));
            steps.pressButton(Button.WISHLIST,1,index);
        }
        steps.checkIfTabHasCorrectStatus("Избранное",ElementStatus.ACTIVE);
        steps.pressWishlistButton("/wish-list");
        steps.checkWishlistPageTitle("Избранное");
        steps.checkProductsTitlesInWishlistPage(addedProductsTitles);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "10.\tПроверка изменения города")
    public void test_10(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();
        steps.pressLocationTab();
        steps.checkIfModalWindowIsDisplayed(true);
        steps.checkSelectCityModalWindowTitle("Выберите город");
        steps.setCity("Краснодар");
        //Selenide.sleep(500);
        steps.checkCurrentCity("Краснодар");
        steps.checkIfModalWindowIsDisplayed(false);

        steps.softAssert.assertAll("Some tests failed");
    }


    @AfterMethod
    public void afterMethod(){
        WebDriverRunner.closeWebDriver();
    }

}
