package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import final_task.enums.ElementStatus;

import java.util.HashMap;
import java.util.Map;

public class PageHeader {

    private final String XPATH_NAVIGATION_TABS = "//div[@class = 'nav-tabs']";
    private final String XPATH_INPUT_CONTAINER = "//div[contains(@class, 'search-container')]";
    private final String XPATH_NESTED_INPUT_FIELD = "/form//div[contains(@class, 'input__container')]";


    private Map<String, SelenideElement> navigationTabs= new HashMap<>();

    private SelenideElement orderStatusTab;
    private SelenideElement profileTab;
    private SelenideElement compareTab;
    private SelenideElement favoritesTab;
    private SelenideElement cartTab;
    private SelenideElement inputTextField;
    private SelenideElement searchButton;


    public PageHeader() {
        orderStatusTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/mvid-header-icon[contains(@class, 'tab-status-order')]");
        profileTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-profile')]/mvid-header-icon");
        compareTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-compare')]/mvid-header-icon");
        favoritesTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-personal')]/mvid-header-icon");
        cartTab = Selenide.$x(XPATH_NAVIGATION_TABS + "/div[contains(@class, 'tab-cart')]/mvid-header-icon");

        navigationTabs.put("Статус заказа", orderStatusTab);
        navigationTabs.put("Войти", profileTab);
        navigationTabs.put("Сравнение", compareTab);
        navigationTabs.put("Избранное", favoritesTab);
        navigationTabs.put("Корзина", cartTab);

        inputTextField = Selenide.$x(XPATH_INPUT_CONTAINER+XPATH_NESTED_INPUT_FIELD+ "/input");
        searchButton = Selenide.$x(XPATH_INPUT_CONTAINER+XPATH_NESTED_INPUT_FIELD+"//div[contains(@class, 'search-icon-wrap')]/mvid-icon");
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

    public ElementStatus getTabStatus(String tabTitle){
        SelenideElement tab = navigationTabs.get(tabTitle);
        tab.should(Condition.exist);
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

}
