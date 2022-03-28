package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

public class PageHeader {

    private final String XPATH_NAVIGATION_TABS = "//div[@class = 'nav-tabs']";

    private Map<String, SelenideElement> navigationTabs= new HashMap<>();

    private SelenideElement orderStatusTab;
    private SelenideElement profileTab;
    private SelenideElement compareTab;
    private SelenideElement favoritesTab;
    private SelenideElement cartTab;

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
    }

    private String getClassAttribute(SelenideElement element) {
        return element.getAttribute("class");
    }

    public ElementStatus getTabStatus(String tabTitle){
        SelenideElement tab = navigationTabs.get(tabTitle);
        if (tab.has(Condition.attribute("class"))){
            if (getClassAttribute(tab).equals("disabled")){
                return ElementStatus.DISABLED;
            }
        }
        return ElementStatus.ACTIVE;
    }
}
