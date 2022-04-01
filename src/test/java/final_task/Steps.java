package final_task;

import com.codeborne.selenide.Selenide;
import org.testng.asserts.SoftAssert;

public class Steps {
    private StartPage startPage;
    private ListingPage listingPage;
    public SoftAssert softAssert;

    public Steps(){
        this.startPage = Selenide.page(new StartPage());
        this.softAssert = new SoftAssert();
    }

    public void checkIfTabHasCorrectStatus(String tabTitle, ElementStatus expectedStatus){
        PageHeader pageHeader = startPage.getPageHeader();
        ElementStatus actualStatus = pageHeader.getTabStatus(tabTitle);
        softAssert.assertEquals(actualStatus, expectedStatus, String.format("проверка статуса кнопки: %s",tabTitle));
    }

    public void clickAddToCartButton(){
        ProductOfTheDay productOfTheDay = startPage.getProductOfTheDay();
        productOfTheDay.clickAddToCartButton();
    }

    public void getNumberOfProductsInCart(int expectedNumber){
        PageHeader pageHeader = startPage.getPageHeader();
        int actualNumber = pageHeader.getNumberOfProductsInCart();
        softAssert.assertEquals(actualNumber, expectedNumber, "количество товаров в корзине");
    }

    public void pressCartButton(String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        Selenide.sleep(200);
        pageHeader.pressCartButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.endsWith(expectedUrlSuffix), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    public void checkCartPageTitle(String expectedCartCageTitle){
        CartPage cartPage = new CartPage();
        String actualCartPageTitle = cartPage.getCartPageHeader();
        softAssert.assertEquals(actualCartPageTitle, expectedCartCageTitle, "отображение заголовка");
    }

    public void checkDayProductTitle(String expectedDayProductTitle){
        ProductOfTheDay productOfTheDay = startPage.getProductOfTheDay();
        String actualDayProductTitle = productOfTheDay.getDayProductTitle();
        softAssert.assertEquals(actualDayProductTitle, expectedDayProductTitle, "отображение заголовка");
    }

    public String saveProductParameter(Section section, Parameter parameter, int indexNumber){
        if (section.equals(Section.MOSTLYSEEN)) {
            switch (parameter) {
                case TITLE:
                    return startPage.saveProductTitle(indexNumber);
                case PRICE:
                    return startPage.saveProductPrice(indexNumber);
            }
        }
        return "wrong request";
    }

    public String saveProductParameter(Section section, Parameter parameter){
        if (section.equals(Section.DAYPRODUCT)){
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

    public String saveProductParameter(Section section, Parameter parameter, int indexRow, int indexInLine){
        if (section.equals(Section.LISTINGPAGE)){
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


    public void checkProductInCartTitle(String expectedTitle, int indexNumber){
        CartPage cartPage = new CartPage();
        String actualTitle = cartPage.getAddedToCartProductTitle(indexNumber);
        softAssert.assertEquals(actualTitle, expectedTitle, "проверка названия товара, добавленного в корзину");

    }

    public void checkTotalPrice(String expectedTotalPrice){
        CartPage cartPage = new CartPage();
        expectedTotalPrice = expectedTotalPrice.replaceAll("[\\D]", "");
        String actualTotalPrice = cartPage.getTotalPrice();
        softAssert.assertEquals(actualTotalPrice, expectedTotalPrice, "проверка полной цены товаров в корзине");
    }

    public void checkNumberOfProductsInCart(String expectedNumberOfProducts){
        CartPage cartPage = new CartPage();
        String actualNumberOfProducts = cartPage.getNumberOfProductsInCart();
        softAssert.assertEquals(actualNumberOfProducts, expectedNumberOfProducts, "проверка количества товаров в корзине");
    }

    public void checkThatCheckoutButtonIsDisplayed(String expectedButtonText){
        CartPage cartPage = new CartPage();
        String actualButtonText = cartPage.getToCheckoutButtonText();
        softAssert.assertEquals(actualButtonText, expectedButtonText, String.format("проверка кнопки %s", expectedButtonText));
    }

    public void checkThatMostlySeenIsDisplayed(){
        softAssert.assertTrue(startPage.checkThatMostlySeenIsDisplayed());
    }

    public void addToCartFromMostlySeen(int indexNumber){
        startPage.addToCartFromMostlySeen(indexNumber);
    }

    public void checkThatInputFieldIdDisplayed(){
        PageHeader pageHeader = startPage.getPageHeader();
        boolean actualStatus = pageHeader.inputFieldIsDisplayed();
        softAssert.assertTrue(actualStatus);
    }

    public void searchText(String searchText, String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.insertTextIntoInputField(searchText);
        pageHeader.pressSearchButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlSuffix) && actualUrl.contains(searchText), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    public void checkProductsTitlesOnPage(String expectedTitle, String expectedSorting){
        listingPage = new ListingPage();
        startPage.setPageViewStyle(ViewStyle.GRID);
        softAssert.assertTrue(listingPage.lookOverProducts(expectedTitle, expectedSorting), String.format("проверяем, что все товары на странице содержат в названии: %s", expectedTitle));
    }

    public void setPageViewStyle(ViewStyle style){
        startPage.setPageViewStyle(style);
    }

    public void checkThatSortDropdownIsDisplayed(String expectedSortingState){
        listingPage = new ListingPage();
        String actualSortingState = listingPage.checkThatSortingIsDisplayed();
        softAssert.assertEquals(actualSortingState, expectedSortingState, String.format("проверка, что сортировка стоит в положении %s", expectedSortingState));
    }

    public void setSortingType(String expectedSortingState){
        listingPage = new ListingPage();
        listingPage.setSortingType(expectedSortingState);
    }

    public void pressLogInButton(){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressLogInButton();
        Selenide.sleep(200);
    }

    public void checkThatCloseButtonIsDisplayed(){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        softAssert.assertTrue(authorizationModalWindow.closeButtonIsDisplayed(),"проверка, что кнопка закрытия отображается");
    }

    public void checkModalWindowTitle(String expectedTitle){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        String actualTitle = authorizationModalWindow.getModalWindowTitle();
        softAssert.assertEquals(actualTitle, expectedTitle, String.format("проверка названия заголовка окна: %s", expectedTitle));
    }

    public void checkPlaceholderText(String expectedPlaceholderText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();

        String actualPlaceholderText = authorizationModalWindow.checkInputField();
        softAssert.assertEquals(actualPlaceholderText, expectedPlaceholderText, String.format("проверка текста плейсхолдера: %s",expectedPlaceholderText));
    }

    public void checkContinueButtonStatus(ElementStatus expectedStatus, String expectedContinueButtonText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        ElementStatus actualStatus = authorizationModalWindow.getContinueButtonStatus();
        softAssert.assertEquals(actualStatus, expectedStatus, "проверка статуса кнопки продолжить");
        String actualContinueButtonText = authorizationModalWindow.getContinueButtonText();
        softAssert.assertEquals(actualContinueButtonText, expectedContinueButtonText, String.format("проверка текста кнопки: %s",actualContinueButtonText));
    }

    public void checkLinkText(String expectedLinkText){
        AuthorizationModalWindow authorizationModalWindow = new AuthorizationModalWindow();
        String actualLinkText = authorizationModalWindow.getLinkText();
        softAssert.assertEquals(actualLinkText,expectedLinkText,String.format("проверка надписи ссылки: %s",expectedLinkText));
    }

    public void pressCompareButton(String expectedUrlSuffix){
        PageHeader pageHeader = startPage.getPageHeader();
        pageHeader.pressCompareButton();
        String actualUrl = startPage.getCurrentUrl();
        softAssert.assertTrue(actualUrl.contains(expectedUrlSuffix), String.format("открытие страницы %s", expectedUrlSuffix));
    }

    public void pressButton(Button buttonToPress, int indexRow, int indexInLine){
        listingPage = new ListingPage();
        listingPage.pressButton(buttonToPress, indexRow, indexInLine);
    }





}
