package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class CartPage {
    private final String XPATH_CART_TITLE = "//span[contains(@class, 'c-header-checkout__logo')]";
    private final String XPATH_ADDED_PRODUCT_TITLE = "//div[contains(@data-product-position, '%d')]";
    private final String XPATH_NESTED_PRODUCT_TITLE = "//div[contains(@class, 'c-cart-item__text-wrapper')]//a[contains(@class, 'c-cart-item__title')]";
    private final String XPATH_TOTAL_PRICE = "//span[contains(@class, 'c-cost-line__text')]";
    private final String XPATH_NUMBER_OF_PRODUCTS = "//div[contains(@class, 'c-cost-line__title-wrap')]/span";
    private final String XPATH_CHECKOUT_BUTTON = "//input[contains(@class, 'sel-common-button-create-order')]";

    private SelenideElement cartPageHeader;
    private SelenideElement addedToCartProductTitle;
    private SelenideElement totalPrice;
    private SelenideElement numberOfProducts;
    private SelenideElement toCheckoutButton;

    public CartPage(){
        cartPageHeader = Selenide.$x(XPATH_CART_TITLE);
        totalPrice = Selenide.$x(XPATH_TOTAL_PRICE);
        numberOfProducts = Selenide.$x(XPATH_NUMBER_OF_PRODUCTS);
        toCheckoutButton = Selenide.$x(XPATH_CHECKOUT_BUTTON);
    }

    public String getCartPageHeader(){
        cartPageHeader.should(Condition.exist);
        return cartPageHeader.getText();
    }

    public String getAddedToCartProductTitle(int indexNumber){
        addedToCartProductTitle = Selenide.$x(String.format(XPATH_ADDED_PRODUCT_TITLE, indexNumber)+ XPATH_NESTED_PRODUCT_TITLE);
        addedToCartProductTitle.should(Condition.exist);
        return addedToCartProductTitle.getText();
    }

    public String getTotalPrice(){
        totalPrice.should(Condition.exist);
        return totalPrice.getText().replaceAll("[\\D]", "");
    }

    public String getNumberOfProductsInCart(){
        numberOfProducts.should(Condition.exist);
        return numberOfProducts.getText();
    }

    public String getToCheckoutButtonText(){
        toCheckoutButton.should(Condition.exist);
        return toCheckoutButton.getAttribute("value");
    }
}
