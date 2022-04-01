package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class ComparePage {

    private final String XPATH_HEADER = "//div[contains(@class, 'c-compare-head-title')]";
    private final String XPATH_NESTED_PAGE_TITLE = "/h1";
    private final String XPATH_NESTED_PRODUCTS_COUNT = "/h1/span";
    private final String XPATH_PRODUCT = "//div[contains(@class, 'c-compare-cell__pinable-product-tile')][%d]";
    private final String XPATH_NESTED_PRODUCT_TITLE = "//div[contains(@class, 'fl-product-tile__title-wrapper')]/h3";

    private SelenideElement pageTitle;
    private SelenideElement productsCount;
    private SelenideElement productTitle;

    public ComparePage(){
        pageTitle = Selenide.$x(XPATH_HEADER+XPATH_NESTED_PAGE_TITLE);
        productsCount = Selenide.$x(XPATH_HEADER+XPATH_NESTED_PRODUCTS_COUNT);
    }

    public String getPageTitle(){
        pageTitle.should(Condition.exist);
        String count = productsCount.getText();
        return pageTitle.getText().replace(count,"");
    }

    private int getProductsCount(){
        pageTitle.should(Condition.exist);
        return Integer.valueOf(productsCount.getText());
    }

    public List<String> collectAllComparedProductsTitles(){
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
