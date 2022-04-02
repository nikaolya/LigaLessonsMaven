package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import final_task.enums.ElementStatus;
import final_task.enums.HeaderTab;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class PageHeader {

    private final String XPATH_NAVIGATION_TABS = "//div[@class = 'nav-tabs']";
    private final String XPATH_INPUT_CONTAINER = "//div[contains(@class, 'search-container')]";
    private final String XPATH_NESTED_INPUT_FIELD = "/form//div[contains(@class, 'input__container')]";

    private final String XPATH_TOP_HEADER = "//div[contains(@class, 'app-header-top')]";
    private final String XPATH_NESTED_LOCATION_TAB = "//div[contains(@class, 'location')]";
    private final String XPATH_NESTED_CURRENT_CITY = "//span[contains(@class, 'location-text')]";

    private Map<HeaderTab, SelenideElement> navigationTabs= new HashMap<>();

    private SelenideElement orderStatusTab;
    private SelenideElement profileTab;
    private SelenideElement compareTab;
    private SelenideElement favoritesTab;
    private SelenideElement cartTab;
    private SelenideElement inputTextField;
    private SelenideElement searchButton;
    private SelenideElement currentCity;
    private SelenideElement locationTab;


    public PageHeader() {
        orderStatusTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/mvid-header-icon[contains(@class, 'tab-status-order')]");
        profileTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-profile')]/mvid-header-icon");
        compareTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-compare')]/mvid-header-icon");
        favoritesTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-personal')]/mvid-header-icon");
        cartTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-cart')]/mvid-header-icon");

        navigationTabs.put(HeaderTab.TAB_STATUS, orderStatusTab);
        navigationTabs.put(HeaderTab.TAB_LOGIN, profileTab);
        navigationTabs.put(HeaderTab.TAB_COMPARE, compareTab);
        navigationTabs.put(HeaderTab.TAB_FAV, favoritesTab);
        navigationTabs.put(HeaderTab.TAB_CART, cartTab);

        inputTextField = Selenide.$x(XPATH_INPUT_CONTAINER+XPATH_NESTED_INPUT_FIELD+ "/input");
        searchButton = Selenide.$x(XPATH_INPUT_CONTAINER+XPATH_NESTED_INPUT_FIELD+"//div[contains(@class, 'search-icon-wrap')]/mvid-icon");

        locationTab = Selenide.$x(XPATH_TOP_HEADER+XPATH_NESTED_LOCATION_TAB);
        currentCity = Selenide.$x(XPATH_TOP_HEADER+XPATH_NESTED_LOCATION_TAB+XPATH_NESTED_CURRENT_CITY);
    }

    static PageHeader getPageHeader() {
        return new PageHeader();
    }

    private String getClassAttribute(SelenideElement element) {
        return element.getAttribute("class");
    }

    public int getNumberOfProductsInCartFromBubble(){
        SelenideElement cartBubble = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-cart')]/mvid-header-icon" + "//mvid-bubble");
        int numberOfProducts = 0;
        if (cartBubble.exists()){
            numberOfProducts = Integer.valueOf(cartBubble.getText());
            System.out.println();
        }
        return numberOfProducts;
    }

    public ElementStatus getTabStatus(HeaderTab tabTitle){
        SelenideElement tab = navigationTabs.get(tabTitle);
        tab.shouldBe(Condition.visible);
        if (tab.has(Condition.attribute("class"))){
            if (getClassAttribute(tab).equals("disabled")){
                return ElementStatus.DISABLED;
            }
        }
        return ElementStatus.ACTIVE;
    }

    public void pressCartButton(){
        SelenideElement cartTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-cart')]/mvid-header-icon" + "//a[contains(@title, 'Корзина')]");
        cartTab.should(Condition.exist);
        Selenide.sleep(2000);
        cartTab.click();
    }

    public void pressCompareButton(){
        SelenideElement cartTab = Selenide.$x(XPATH_NAVIGATION_TABS +
                "/div[contains(@class, 'tab-compare')]/mvid-header-icon" + "//a[contains(@title, 'Сравнение')]");
        cartTab.should(Condition.exist);
        Selenide.sleep(2000);
        cartTab.click();
    }

    public void pressLogInButton(){
        SelenideElement logInTab = Selenide.$x(XPATH_NAVIGATION_TABS +
                "/div[contains(@class, 'tab-profile')]/mvid-header-icon" + "//a[contains(@title, 'Личный кабинет')]");
        logInTab.should(Condition.exist);
        logInTab.click();
    }


    public void pressWishlistButton(){
        SelenideElement wishlistTab = Selenide.$x(XPATH_NAVIGATION_TABS +
                "/div[contains(@class, 'tab-personal')]/mvid-header-icon" + "//a[contains(@title, 'Избранное')]");
        wishlistTab.should(Condition.exist);
        wishlistTab.click();
    }


    public boolean inputFieldIsDisplayed(){
        inputTextField.should(Condition.exist);
        return inputTextField.isDisplayed();
    }

    public void insertTextIntoInputField(String searchText){
        inputTextField.should(Condition.exist);
        inputTextField.setValue(searchText);
    }

    public void pressSearchButton(){
        searchButton.should(Condition.exist);
        searchButton.click();
    }

    public void pressLocationTab(){
        locationTab.should(Condition.exist);
        locationTab.click();
    }

    public String checkCurrentCity(String expectedCity){
        currentCity.shouldHave(Condition.text(expectedCity),Duration.ofSeconds(30));
        return currentCity.getText();
    }


}

