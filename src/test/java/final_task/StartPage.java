package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class StartPage {
    private static StartPage startPage;

    // Шапка страницы
    public PageHeader getPageHeader() {
        return PageHeader.getPageHeader();
    }

    // Секция Товары дня
    public ProductOfTheDay getProductOfTheDay() {
        return ProductOfTheDay.getProductOfTheDay();
    }

    // Пагинация
    public Pagination getPagination(){
        return Pagination.getPagination();
    }


    private final String XPATH_MOSTLY_SEEN = "//mvid-simple-product-collection[./h2[text()='Самые просматриваемые']]//mvid-product-cards-group";
    private final String XPATH_NESTED_ADD_TO_CART_BUTTONS = "//div[contains(@class, 'product-mini-card__controls')]";
    private final String XPATH_NESTED_PRODUCT_TITLE = "//div[contains(@class, 'product-mini-card__name')]";
    private final String XPATH_NESTED_PRODUCT_PRICE = "//div[contains(@class, 'product-mini-card__price')]";
    private SelenideElement mostlySeenCategory;
    private SelenideElement addToCartButton;
    private SelenideElement productTitle;
    private SelenideElement productPrice;

    private SelenideElement viewSwitcher;
    private SelenideElement viewState;


    public StartPage(){
        mostlySeenCategory = Selenide.$x(XPATH_MOSTLY_SEEN);

        viewSwitcher = Selenide.$x("//div[@class = 'listing-view-switcher']//button");
        viewState = Selenide.$x("//div[contains(@class, 'listing-view-switcher__pointer')]");
    }

    public String getCurrentUrl(){
        Selenide.sleep(2000);
        return WebDriverRunner.currentFrameUrl();
    }

    public void addToCartFromMostlySeen(int indexNumber){
        addToCartButton = Selenide.$x(XPATH_MOSTLY_SEEN + XPATH_NESTED_ADD_TO_CART_BUTTONS + String.format("[%d]//button[contains(@title, 'Добавить в корзину')]",indexNumber));
        addToCartButton.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        addToCartButton.should(Condition.exist);
        addToCartButton.click();
    }

    public boolean checkThatMostlySeenIsDisplayed(){
        scrollToMostlySeen();
        mostlySeenCategory.should(Condition.exist);
        return mostlySeenCategory.isDisplayed();
    }

    public String saveProductTitle(int indexNumber){
        productTitle = Selenide.$x(XPATH_MOSTLY_SEEN + XPATH_NESTED_PRODUCT_TITLE + String.format("[%d]/div/a/div",indexNumber));
        productTitle.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        productTitle.should(Condition.exist);
        return productTitle.getText();
    }

    public String saveProductPrice(int indexNumber){
        productPrice = Selenide.$x(XPATH_MOSTLY_SEEN + XPATH_NESTED_PRODUCT_PRICE + String.format("[%d]//span[(contains(@class, 'price__main-value'))]",indexNumber));
        productPrice.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        productPrice.should(Condition.exist);
        return productPrice.getText().replaceAll("[\\D]", "");
    }
    
    private void scrollToMostlySeen(){
        SelenideElement container;
        container = Selenide.$x("//mvid-simple-product-collection-mp[1]");
        container.scrollTo();
        if (!mostlySeenCategory.isDisplayed()) {
            container = Selenide.$x("//mvid-simple-product-collection-mp[2]");
            container.scrollTo();
        }
    }

    public void setPageViewStyle(ViewStyle style){
        viewSwitcher.should(Condition.exist);
        String listingType = viewState.getAttribute("class");
        switch (style){
            case GRID:{
                if (listingType.contains("listing-view-switcher__pointer--list")){
                    viewSwitcher.click();
                }
                break;
            }
            case LIST:{
                if (listingType.contains("listing-view-switcher__pointer--grid")){
                    viewSwitcher.click();
                }
                break;
            }
        }
    }

}
