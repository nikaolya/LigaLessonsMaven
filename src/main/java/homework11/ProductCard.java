package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class ProductCard {

    private SelenideElement title;
    private SelenideElement priceLabel;
    private SelenideElement oldPriceLabel;
    private SelenideElement addToCartButton;
    private SelenideElement addToFavoritesButton;
    private SelenideElement addToComparisonButton;
    private SelenideElement starRating;
    private SelenideElement reviews;
    private SelenideElement mBonusValue;
    private SelenideElement mBonusIcon;
    private SelenideElement bonusRubles;

    private final String XPATH_TITLE_LINE_CONTAINER = "//div[contains(@class, 'product-card__title-line-container')]";
    private final String XPATH_NESTED_NAME_LINK = "[mvid-plp-product-title[.//a[text() = ' %s ']]]";

    private ProductCard(String productName) {
        String titleContainerPath = String.format(XPATH_TITLE_LINE_CONTAINER + XPATH_NESTED_NAME_LINK, productName);
        title = Selenide.$x(titleContainerPath+"//a");
        priceLabel = Selenide.$x(titleContainerPath +
                "/following-sibling::div[contains(@class, 'product-card__price-block-container')]" +
                "//span[@class = 'price__main-value']");
        String followingSiblingProductCheckoutButtonsContainer = "/following-sibling::mvid-plp-product-checkout";
        addToCartButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//button[span[text() = 'В корзину']]");
        addToFavoritesButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//div[contains(@class, 'wishlist-button-block')]//button");
        addToComparisonButton = Selenide.$x(titleContainerPath +
                followingSiblingProductCheckoutButtonsContainer + "//div[contains(@class, 'compare-button-block')]//button");


        oldPriceLabel = Selenide.$x(titleContainerPath +
                "/following-sibling::div[2]" +
                "//span[contains(@class, 'price__sale-value')]");

        starRating = Selenide.$x(titleContainerPath + "/mvid-plp-product-rating//span[contains(@class, 'value') and contains (@class, 'ng-star-inserted')]");

        reviews = Selenide.$x(titleContainerPath + "/mvid-plp-product-rating//span[contains(@class, 'product-rating__feedback')]");

        mBonusValue = Selenide.$x(titleContainerPath + "/following-sibling::div[contains(@class, 'product-card__mbonus-block-container')]" + "//span[contains(@class, 'mbonus-block__value')]");

        mBonusIcon = Selenide.$x(titleContainerPath + "/following-sibling::div[contains(@class, 'product-card__mbonus-block-container')]" + "//use");

        bonusRubles = Selenide.$x(titleContainerPath + "/following-sibling::div[contains(@class, 'product-card__mbonus-block-container')]" + "//span[contains(@class, 'mbonus-block__text-label')]");

    }

    static ProductCard getProductCard(String productName) {
        return new ProductCard(productName);
    }

    public SelenideElement getTitle() {
        return title;
    }

    public SelenideElement getPriceLabel()  {
        return priceLabel;
    }

    public SelenideElement getOldPriceLabel() {
        return oldPriceLabel;
    }

    public SelenideElement getAddToCartButton() {
        return addToCartButton;
    }

    public SelenideElement getAddToFavoritesButton() {
        return addToFavoritesButton;
    }

    public SelenideElement getAddToComparisonButton() {
        return addToComparisonButton;
    }

    public SelenideElement getStarRating() {
        return starRating;
    }

    public SelenideElement getReviews() {
        return reviews;
    }

    public SelenideElement getmBonusValue() {
        return mBonusValue;
    }

    public SelenideElement getmBonusIcon() {
        return mBonusIcon;
    }

    public SelenideElement getBonusRubles() {
        return bonusRubles;
    }


}

