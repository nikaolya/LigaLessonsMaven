package final_task.steps;

import com.codeborne.selenide.Selenide;
import final_task.enums.*;
import final_task.page_object.*;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.NoSuchElementException;

public class Steps {
    private StartPage startPage;
    private ListingPage listingPage;
    public SoftAssert softAssert;

    public Steps(){
        this.startPage = Selenide.page(new StartPage());
        this.softAssert = new SoftAssert();
    }

    // PageHeader
    public void checkIfTabHasCorrectStatus(HeaderTab tabTitle, ElementStatus expectedStatus){
        PageHeader pageHeader = startPage.getPageHeader();
        ElementStatus actualStatus = pageHeader.getTabStatus(tabTitle);
        softAssert.assertEquals(actualStatus, expectedStatus, String.format("проверка статуса кнопки: %s",tabTitle.toString()));
    }

    // PageHeader
    public void getNumberOfProductsInCartFromBubble(int expectedNumber){
        PageHeader pageHeader = startPage.getPageHeader();
        int actualNumber = pageHeader.getNumberOfProductsInCartFromBubble();
        softAssert.assertEquals(actualNumber, expectedNumber, "количество товаров в корзине");
    }

    // PageHeader
    public void pressCartButton(String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        Selenide.sleep(200);
        pageHeader.pressCartButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.endsWith(expectedUrlSuffix), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    // PageHeader
    public void pressLogInButton(){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressLogInButton();
        Selenide.sleep(200);
    }

    // PageHeader
    public void pressLocationTab(){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressLocationTab();
    }

    // PageHeader
    public void checkCurrentCity(String expectedCity){
        PageHeader pageHeader = startPage.getPageHeader();
        String actualCity = pageHeader.checkCurrentCity(expectedCity);
        softAssert.assertEquals(actualCity,expectedCity,String.format("проверка текущего города: %s",expectedCity));
    }

    // ProductOfTheDay
    public void clickAddToCartButton(){
        ProductOfTheDay productOfTheDay = startPage.getProductOfTheDay();
        productOfTheDay.clickAddToCartButton();
    }

    // ProductOfTheDay
    public void checkDayProductTitle(String expectedDayProductTitle){
        ProductOfTheDay productOfTheDay = startPage.getProductOfTheDay();
        String actualDayProductTitle = productOfTheDay.getDayProductTitle();
        softAssert.assertEquals(actualDayProductTitle, expectedDayProductTitle, "отображение заголовка");
    }

    // ProductOfTheDay
    public String saveProductParameter(Section section, Parameter parameter){
        if (section.equals(Section.DAY_PRODUCT)){
            ProductOfTheDay productOfTheDay = startPage.getProductOfTheDay();
            switch(parameter){
                case TITLE:
                    return productOfTheDay.saveProductTitle();
                case PRICE:
                    return productOfTheDay.saveProductPrice();
            }
        }
        return "wrong request";
    }

    // CartPage
    public void checkCartPageTitle(String expectedCartCageTitle){
        CartPage cartPage = new CartPage();
        String actualCartPageTitle = cartPage.getCartPageHeader();
        softAssert.assertEquals(actualCartPageTitle, expectedCartCageTitle, "отображение заголовка");
    }

    // CartPage
    public void checkProductsTitlesInCart(List<String> expectedTitles){
        CartPage cartPage = new CartPage();
        List<String> actualTitles = cartPage.collectAllProductsInCartTitles();
        softAssert.assertTrue(actualTitles.size() == expectedTitles.size() && actualTitles.containsAll(expectedTitles) && expectedTitles.containsAll(actualTitles), "проверка названий товаров, добавленных в корзину");
    }

    // CartPage
    public void checkTotalPrice(List<String> addedProductsPrices){
        CartPage cartPage = new CartPage();
        int totalPrice = 0;
        for (int i = 0; i < addedProductsPrices.size(); i++) {
            totalPrice += Integer.valueOf(addedProductsPrices.get(i));
        }
        String expectedTotalPrice = Integer.toString(totalPrice);
        String actualTotalPrice = cartPage.getTotalPrice();
        softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "проверка полной цены товаров в корзине");
    }

    // CartPage
    public void checkNumberOfProductsInCart(String expectedNumberOfProducts){
        CartPage cartPage = new CartPage();
        String actualNumberOfProducts = cartPage.getNumberOfProductsInCart();
        softAssert.assertEquals(actualNumberOfProducts, expectedNumberOfProducts, "проверка количества товаров в корзине");
    }

    // CartPage
    public void checkThatCheckoutButtonIsDisplayed(String expectedButtonText){
        CartPage cartPage = new CartPage();
        String actualButtonText = cartPage.getToCheckoutButtonText();
        softAssert.assertEquals(actualButtonText, expectedButtonText, String.format("проверка кнопки %s", expectedButtonText));
    }

    // StartPage
    public String saveProductParameter(Section section, Parameter parameter, int indexNumber){
        if (section.equals(Section.MOSTLY_SEEN)) {
            switch (parameter) {
                case TITLE:
                    return startPage.saveProductTitle(indexNumber);
                case PRICE:
                    return startPage.saveProductPrice(indexNumber);
            }
        }
        return "wrong request";
    }

    // StartPage
    public void checkThatMostlySeenIsDisplayed(){
        softAssert.assertTrue(startPage.checkThatMostlySeenIsDisplayed());
    }

    // StartPage
    public void addToCartFromMostlySeen(int indexNumber){
        startPage.addToCartFromMostlySeen(indexNumber);
    }

    // StartPage
    public void checkThatInputFieldIdDisplayed(){
        PageHeader pageHeader = startPage.getPageHeader();
        boolean actualStatus = pageHeader.inputFieldIsDisplayed();
        softAssert.assertTrue(actualStatus);
    }

    // StartPage
    public void searchText(String searchText, String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.insertTextIntoInputField(searchText);
        pageHeader.pressSearchButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlSuffix) && actualUrl.contains(searchText), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    // StartPage
    public void setPageViewStyle(ViewStyle style){
        startPage.setPageViewStyle(style);
    }

    // StartPage
    public void pressCompareButton(String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressCompareButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlSuffix), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    // StartPage
    public void pressWishlistButton(String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressWishlistButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlSuffix), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    // ListingPage
    public String saveProductParameter(Section section, Parameter parameter, int indexRow, int indexInLine){
        if (section.equals(Section.LISTING_PAGE)){
            ListingPage listingPage = new ListingPage();
            switch(parameter){
                case TITLE:
                    return listingPage.getProductTitle(indexRow, indexInLine);
                case PRICE:
                    return Integer.toString(listingPage.getProductPrice(indexRow, indexInLine));
            }
        }
        return "wrong request";
    }

    // ListingPage
    public void checkProductsTitlesOnPage(String expectedTitle, String expectedSorting){
        listingPage = new ListingPage();
        startPage.setPageViewStyle(ViewStyle.GRID);
        softAssert.assertTrue(listingPage.lookOverProducts(expectedTitle, expectedSorting), String.format("проверяем, что все товары на странице содержат в названии: %s и сортировка: %s" , expectedTitle, expectedSorting));
    }

    // ListingPage
    public void checkThatSortDropdownIsDisplayed(String expectedSortingState){
        listingPage = new ListingPage();
        String actualSortingState = listingPage.checkThatSortingIsDisplayed();
        softAssert.assertEquals(actualSortingState, expectedSortingState, String.format("проверка, что сортировка стоит в положении %s", expectedSortingState));
    }

    // ListingPage
    public void setSortingType(String expectedSortingState){
        listingPage = new ListingPage();
        listingPage.setSortingType(expectedSortingState);
    }

    // ListingPage
    public void pressButton(ProductButton buttonToPress, int indexRow, int indexInLine){
        listingPage = new ListingPage();
        listingPage.pressButton(buttonToPress, indexRow, indexInLine);
    }

    // AuthorizationModalWindow
    public void checkThatCloseButtonIsDisplayed(){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        softAssert.assertTrue(authorizationModalWindow.closeButtonIsDisplayed(),"проверка, что кнопка закрытия отображается");
    }

    // AuthorizationModalWindow
    public void checkModalWindowTitle(String expectedTitle){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        String actualTitle = authorizationModalWindow.getModalWindowTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, String.format("проверка названия заголовка окна: %s", expectedTitle));
    }

    // AuthorizationModalWindow
    public void checkPlaceholderText(String expectedPlaceholderText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();

        String actualPlaceholderText = authorizationModalWindow.checkInputField();
        softAssert.assertEquals(actualPlaceholderText, expectedPlaceholderText, String.format("проверка текста плейсхолдера: %s",expectedPlaceholderText));
    }

    // AuthorizationModalWindow
    public void checkContinueButtonStatus(ElementStatus expectedStatus, String expectedContinueButtonText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        ElementStatus actualStatus = authorizationModalWindow.getContinueButtonStatus();
        softAssert.assertEquals(actualStatus, expectedStatus, "проверка статуса кнопки продолжить");
        String actualContinueButtonText = authorizationModalWindow.getContinueButtonText();
        softAssert.assertEquals(actualContinueButtonText, expectedContinueButtonText, String.format("проверка текста кнопки: %s",actualContinueButtonText));
    }

    // AuthorizationModalWindow
    public void checkLinkText(String expectedLinkText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        String actualLinkText = authorizationModalWindow.getLinkText();
        softAssert.assertEquals(actualLinkText,expectedLinkText,String.format("проверка надписи ссылки: %s",expectedLinkText));
    }

    // ComparePage
    public void checkComparePageTitle(String expectedTitle){
        ComparePage comparePage = new ComparePage();
        String actualTitle = comparePage.getPageTitle();
        softAssert.assertEquals(actualTitle,expectedTitle,String.format("проверка заголовка страницы сравнения товаров: %s",expectedTitle));
    }

    // ComparePage
    public void checkProductsTitlesInComparedPage(List<String> expectedTitles){
        ComparePage comparePage = new ComparePage();
        List<String> actualTitles = comparePage.collectAllComparedProductsTitles();
        softAssert.assertTrue(actualTitles.size() == expectedTitles.size() && actualTitles.containsAll(expectedTitles) && expectedTitles.containsAll(actualTitles), "проверка заголовков товаров на странице сравнения");
    }

    // WishlistPage
    public void checkWishlistPageTitle(String expectedTitle){
        WishlistPage wishlistPage = new WishlistPage();
        String actualTitle = wishlistPage.getPageTitle();
        softAssert.assertEquals(actualTitle,expectedTitle,String.format("проверка заголовка страницы избранного: %s",expectedTitle));
    }

    // WishlistPage
    public void checkProductsTitlesInWishlistPage(List<String> expectedTitles){
        WishlistPage wishlistPage = new WishlistPage();
        List<String> actualTitles = wishlistPage.collectAllWishedProductsTitles();
        softAssert.assertTrue(actualTitles.size() == expectedTitles.size() && actualTitles.containsAll(expectedTitles) && expectedTitles.containsAll(actualTitles), "проверка заголовков товаров на странице избранного");
    }

    // SelectCityModalWindow
    public void checkSelectCityModalWindowTitle(String expectedTitle){
        SelectCityModalWindow selectCityModalWindow = new SelectCityModalWindow();
        String actualTitle = selectCityModalWindow.getSelectCityModalWindowTitle();
        softAssert.assertEquals(actualTitle,expectedTitle,String.format("проверка заголовка окна выбора города: %s",expectedTitle));
    }

    // SelectCityModalWindow
    public void setCity(String city){
        SelectCityModalWindow selectCityModalWindow = new SelectCityModalWindow();
        selectCityModalWindow.setCity(city);
    }

    // SelectCityModalWindow
    public void checkIfModalWindowIsDisplayed(boolean expectedStatus){
        SelectCityModalWindow selectCityModalWindow = new SelectCityModalWindow();
        boolean actualStatus;
        try{
            actualStatus = selectCityModalWindow.windowIsDisplayed();
        } catch(NoSuchElementException e){
            actualStatus = false;
        }
        softAssert.assertEquals(actualStatus,expectedStatus,"проверка состояния модального окна");
    }
}
