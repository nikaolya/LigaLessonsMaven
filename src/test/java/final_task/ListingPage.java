package final_task;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class ListingPage {

    // Пагинация
    public Pagination getPagination(){
        return Pagination.getPagination();
    }

    private final String XPATH_SORT = "//mvid-dropdown[contains(@icontype, 'sort')]";
    private final String XPATH_NESTED_SORT_BUTTON = "/div[contains(@class, 'dropdown')]";
    private final String XPATH_NESTED_SORT_OPTIONS = "//div[contains(@class, 'dropdown__options')]";
    private final String XPATH_NESTED_SELECTED_SORT = "//div[contains(@class, 'dropdown__title')]/span";

    private final String XPATH_PRODUCT = "//div[contains(@class, 'product-cards-row')][%d]";
    private final String XPATH_NESTED_TITLE = "//div[contains(@class, 'product-card__title-line-container')][%d]//a[contains(@class, 'product-title__text')]";
    private final String XPATH_NESTED_PRICE_CONTAINER = "//div[contains(@class, 'product-card__price-block-container')][%d]";
    private final String XPATH_NESTED_PRICE = "//span[contains(@class, 'price__main-value')]";

    private final String XPATH_NESTED_CHECKOUT = "//mvid-plp-product-checkout[contains(@class, 'product-card__checkout')][%d]";
    private final String XPATH_NESTED_ADD_TO_CART_BUTTON = "//mvid-plp-cart-button//button";
    private final String XPATH_NESTED_FAVORITES_TAB = "//div[contains(@class, 'wishlist-button-block')]//button";
    private final String XPATH_NESTED_COMPARE_TAB = "//div[contains(@class, 'compare-button-block')]//button";

    private SelenideElement sortDropdown;
    private SelenideElement sortState;

    public ListingPage(){
        sortDropdown = Selenide.$x(XPATH_SORT+XPATH_NESTED_SORT_BUTTON);
        sortState = Selenide.$x(XPATH_SORT+XPATH_NESTED_SELECTED_SORT);
    }

    private int getNumberOfProductsDisplayedOnPage(){
        SelenideElement productsOnPage = Selenide.$x("//mvid-dropdown[contains(@class, 'plp__pagination-limit')]//span");
        String[] str = productsOnPage.getText().split(" ");
        return Integer.valueOf(str[str.length-1]);
    }

    private int getTotalNumberOfProducts(){
        SelenideElement numberOfProducts = Selenide.$x("//div[contains(@class, 'tabs')]/button[1]/span");
        //SelenideElement numberOfProducts = Selenide.$x("//p[contains(@class, 'srp-title')]");
        String str = numberOfProducts.getText();
        return Integer.valueOf(str);
    }

    public boolean lookOverProducts(String expectedTitle, String expectedSorting){
        Pagination pagination = getPagination();
        int productsOnPage = getNumberOfProductsDisplayedOnPage();
        int numberOfProducts = getTotalNumberOfProducts();
        int indexInLine = 1;
        int indexRow = 1;
        int onOnePage = 0; // счетчик товаров на одной странице
        int count = 0; // общий счетчик
        int previousProductPrice = Integer.MAX_VALUE;
        int currentProductPrice;
        String currentProductTitle;
        while(true){
            while (true){
                // Начинаем с первого товара на странице и мотаем до последнего элемента на странице
                currentProductTitle = getProductTitle(indexRow, indexInLine);

                // при встрече первого товара, не удовлетворяющего условию проверки, возвращаем false
                if (!currentProductTitle.toLowerCase().contains(expectedTitle)){
                    return false;
                }

                if (expectedSorting.equals("Сначала дороже")){
                    currentProductPrice = getProductPrice(indexRow,indexInLine);
                    if (currentProductPrice > previousProductPrice){
                        return false;
                    }
                    if (currentProductPrice!=0) {
                        previousProductPrice = currentProductPrice;
                    }
                }

                onOnePage++;
                count++;
                indexInLine++;

                // если проверили все товары в ряду, переходим на следующий ряд
                if (indexInLine == 5){
                    indexRow++;
                    indexInLine = 1;
                }

                // если проверили все товары на странице, обнуляем счетчик перед переходом на следующую страницу
                if (onOnePage == productsOnPage){
                    onOnePage = 0;
                    break;
                }

                // если все товары проверили, останавливаемся
                if (count == numberOfProducts){
                    // если все товары прошли проверку, то все ок
                    return true;
                }
            }
            // если дошли до конца страницы, открываем следующую страницу
            indexInLine = 1;
            indexRow = 1;
            pagination.scrollRight();
        }
    }


    public int getProductPrice(int indexRow, int indexInLine){
        String priceXpath = String.format(XPATH_PRODUCT+XPATH_NESTED_PRICE_CONTAINER, indexRow, indexInLine);
        SelenideElement priceContainer = Selenide.$x(priceXpath);
        priceContainer.should(Condition.exist);
        if (indexRow==1){
            Selenide.sleep(500);
        }
        if(!(priceContainer.getAttribute("class").contains("product-card__price-block-container--not-first-placed"))){
            SelenideElement productPrice = Selenide.$x(priceXpath+XPATH_NESTED_PRICE);
            productPrice.should(Condition.exist);
            String str = productPrice.getText().replaceAll("[\\D]", "");
            return Integer.valueOf(str);
        } else {
            // если цены у товара нет, возвращаем 0
            return 0;
        }
    }

    public String getProductTitle(int indexRow, int indexInLine){
        String titleXpath = String.format(XPATH_PRODUCT+XPATH_NESTED_TITLE, indexRow, indexInLine);
        SelenideElement productTitle = Selenide.$x(titleXpath);
        productTitle.scrollIntoView("{behavior: \"smooth\", block: \"center\", inline: \"center\"}");
        productTitle.should(Condition.exist);
        return productTitle.getText();
    }

    public void pressButton(Button buttonToPress, int indexRow, int indexInLine){
        String xpath;
        switch(buttonToPress){
            case ADD_TO_CART:
                xpath = XPATH_NESTED_ADD_TO_CART_BUTTON;
                break;
            case WISHLIST:
                xpath = XPATH_NESTED_FAVORITES_TAB;
                break;
            case COMPARE:
                xpath = XPATH_NESTED_COMPARE_TAB;
                break;
            default:
                xpath = "";
        }
        String buttonXpath = String.format(XPATH_PRODUCT+XPATH_NESTED_CHECKOUT+xpath, indexRow, indexInLine);
        SelenideElement button = Selenide.$x(buttonXpath);
        button.should(Condition.exist);
        button.click();
    }

    public String checkThatSortingIsDisplayed(){
        sortDropdown.should(Condition.exist);
        return sortState.getText();
    }

    public void setSortingType(String expectedSortingState){
        sortDropdown.click();
        SelenideElement sortStateToSet = Selenide.$x(XPATH_SORT+XPATH_NESTED_SORT_OPTIONS+ String.format("/div[text() = ' %s ']", expectedSortingState));
        sortStateToSet.should(Condition.exist);
        sortStateToSet.click();
    }

}
