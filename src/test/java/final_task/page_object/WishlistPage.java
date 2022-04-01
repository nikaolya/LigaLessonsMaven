package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class WishlistPage {
    private final String XPATH_HEADER = "//section[contains(@class, 'wishlist-topline')]";
    private final String XPATH_NESTED_PAGE_TITLE = "/h1";
    private final String XPATH_NESTED_PRODUCTS_COUNT = "/strong[contains(@class, 'wishlist-quantity')]";
    private final String XPATH_PRODUCT = "//div[contains(@class, 'wishlist-item-holder')][%d]";
    private final String XPATH_NESTED_PRODUCT_TITLE = "//h3[contains(@class, 'wishlist-product-title')]/a";

    private SelenideElement pageTitle;
    private SelenideElement productsCount;
    private SelenideElement productTitle;

    public WishlistPage(){
        pageTitle = Selenide.$x(XPATH_HEADER+XPATH_NESTED_PAGE_TITLE);
        productsCount = Selenide.$x(XPATH_HEADER+XPATH_NESTED_PRODUCTS_COUNT);
    }

    public String getPageTitle(){
        pageTitle.should(Condition.exist);
        return pageTitle.getText();
    }

    private int getProductsCount(){
        pageTitle.should(Condition.exist);
        return Integer.valueOf(productsCount.getText());
    }

    public List<String> collectAllWishedProductsTitles(){
        int count = getProductsCount();
        List<String> actualTitles = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            productTitle = Selenide.$x(String.format(XPATH_PRODUCT,i)+XPATH_NESTED_PRODUCT_TITLE);
            productTitle.should(Condition.exist);
            actualTitles.add(productTitle.getText());
        }
        return actualTitles;
    }
}
