package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.Objects;

public class PageObject {

    private static PageObject listingPage;

    public ProductCard getProductCard(String productName) {
        return ProductCard.getProductCard(productName);
    }

    public Pagination getPagination(){
        return Pagination.getPagination();
    }

    public ListingViewSwitcher getListingViewSwitcher() {
        return ListingViewSwitcher.getListingViewSwitcher();
    }

    public Filter getFilter(String categoryName, String filterName) {
        return Filter.getFilter(categoryName, filterName);
    }

    public PageObject() {
    }

    public static PageObject getPageObject() {
        if (Objects.isNull(listingPage)) listingPage = Selenide.page(new PageObject());
        return listingPage;
    }

    public static SelenideElement scrollDown(SelenideElement element){
        SelenideElement paginationLimit = Selenide.$x("//mvid-dropdown[contains(@class, 'plp__pagination-limit')]//span");
        String[] str = paginationLimit.getText().split(" ");
        int elementsOnPage = Integer.valueOf(str[str.length-1]);
        System.out.println();

        int i = 1;
        SelenideElement first;
        while (!element.exists()){
            first = Selenide.$x(String.format("(//div[contains(@class, 'product-card__title-line-container')])[%d]", i));
            first.scrollIntoView("{behavior: \"smooth\", block: \"start\", inline: \"nearest\"}");
            i = i+1;
            if (i >= elementsOnPage){
                break;
            }
        }
        Selenide.sleep(1000);
        return element;
    }

}
