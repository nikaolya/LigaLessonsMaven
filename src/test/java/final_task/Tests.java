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
        steps.getNumberOfProductsInCart(1);

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "3.\tПереход в корзину")
    public void test_3(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkDayProductTitle("Товары дня");
        String addedProductTitle = steps.saveProductParameter(Section.DAYPRODUCT, Parameter.TITLE);
        String addedProductPrice = steps.saveProductParameter(Section.DAYPRODUCT, Parameter.PRICE);
        steps.clickAddToCartButton();
        steps.pressCartButton("/cart");
        steps.checkCartPageTitle("Моя корзина");
        steps.checkProductInCartTitle(addedProductTitle, 1);
        steps.checkThatCheckoutButtonIsDisplayed("Перейти к оформлению");
        steps.checkTotalPrice(addedProductPrice);
        steps.checkNumberOfProductsInCart("В корзине 1 товар");

        steps.softAssert.assertAll("Some tests failed");
    }

    @Test(testName = "4.\tДобавление в корзину два товара")
    public void test_4(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();

        steps.checkThatMostlySeenIsDisplayed();
        String addedProductTitle1 = steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.TITLE, 1);
        String addedProductPrice1 = steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.PRICE, 1);
        steps.addToCartFromMostlySeen(1);
        String addedProductTitle2 = steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.TITLE, 2);
        String addedProductPrice2 = steps.saveProductParameter(Section.MOSTLYSEEN, Parameter.PRICE, 2);
        steps.addToCartFromMostlySeen(2);
        steps.pressCartButton("/cart");
        steps.checkNumberOfProductsInCart("В корзине 2 товара");
        steps.checkProductInCartTitle(addedProductTitle1, 1);
        steps.checkProductInCartTitle(addedProductTitle2, 2);
        String addedProductsPrice = Integer.toString(Integer.valueOf(addedProductPrice1)+Integer.valueOf(addedProductPrice2));
        steps.checkTotalPrice(addedProductsPrice);

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
        //steps.checkProductsTitlesOnPage("apple", "");
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
    public void text_8(){
        Selenide.open("https://www.mvideo.ru");
        Steps steps = new Steps();
        steps.checkThatInputFieldIdDisplayed();
        steps.searchText("apple", "/product-list-page");

        String addedProductTitle = steps.saveProductParameter(Section.LISTINGPAGE, Parameter.TITLE, 1,1);
        steps.pressButton(Button.COMPARE,1,1);
        String addedProductTitle2 = steps.saveProductParameter(Section.LISTINGPAGE, Parameter.TITLE, 1,2);
        steps.pressButton(Button.COMPARE,1,2);
        String addedProductTitle3 = steps.saveProductParameter(Section.LISTINGPAGE, Parameter.TITLE, 1,3);
        steps.pressButton(Button.COMPARE,1,3);
        steps.checkIfTabHasCorrectStatus("Сравнение",ElementStatus.ACTIVE);
        steps.pressCompareButton("/product-comparison");

        // НЕ ДОДЕЛАНО

        steps.softAssert.assertAll("Some tests failed");
    }


    @AfterMethod
    public void afterMethod(){
        WebDriverRunner.closeWebDriver();
    }

}
