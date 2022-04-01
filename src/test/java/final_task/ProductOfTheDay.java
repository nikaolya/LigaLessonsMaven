package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

public class ProductOfTheDay {

    private Map<String, SelenideElement> dayProductSection= new HashMap<>();

    private final String XPATH_DAY_PRODUCT_CONTAINER = "//mvid-day-products-block[contains(@class, 'block')]";
    private final String XPATH_DAY_PRODUCTS = "//div[contains(@class, 'product-container')]";
    private final String XPATH_MAIN_DAY_PRODUCT = "/mvid-day-product[contains(@class, 'main')]";

    private SelenideElement sectionTitle;
    private SelenideElement productTitle;
    private SelenideElement productPrice;
    private SelenideElement cartButton;

    public ProductOfTheDay(){
        sectionTitle = Selenide.$x(XPATH_DAY_PRODUCT_CONTAINER + "//mvid-title-timer//span[contains(@class, 'title__text')]");
        productTitle = Selenide.$x(XPATH_DAY_PRODUCT_CONTAINER + XPATH_DAY_PRODUCTS + XPATH_MAIN_DAY_PRODUCT + "//div[contains(@class, 'title')]/a");
        productPrice = Selenide.$x(XPATH_DAY_PRODUCT_CONTAINER + XPATH_DAY_PRODUCTS + XPATH_MAIN_DAY_PRODUCT +"//span[contains(@class, 'price__main-value')]");
        cartButton = Selenide.$x(XPATH_DAY_PRODUCT_CONTAINER + XPATH_DAY_PRODUCTS + XPATH_MAIN_DAY_PRODUCT + "//div[contains(@class, 'title')]/following-sibling::button");

    }

    static ProductOfTheDay getProductOfTheDay() {
        return new ProductOfTheDay();
    }

    public void clickAddToCartButton(){
        cartButton.should(Condition.exist);
        cartButton.click();
    }

    public String getDayProductTitle(){
        sectionTitle.should(Condition.exist);
        return sectionTitle.getText();
    }

    public String saveProductTitle(){
        productTitle.should(Condition.exist);
        return productTitle.getText();
    }

    public String saveProductPrice(){
        productPrice.should(Condition.exist);
        return productPrice.getText().replaceAll("[\\D]", "");
    }


}
