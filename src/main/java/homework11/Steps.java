package homework11;

import com.codeborne.selenide.Condition;

import static homework11.PageObject.scrollDown;

public class Steps {

    private PageObject listingPage;

    public Steps(){
        listingPage = PageObject.getPageObject();
    }

    public void findElement(String title){
        ListingViewSwitcher listingViewSwitcher = listingPage.getListingViewSwitcher();
        listingViewSwitcher.setGridView();
        Pagination pagination = listingPage.getPagination();

        ProductCard productCard = listingPage.getProductCard(title);
        while (!productCard.getPriceLabel().exists()){
            scrollDown(productCard.getPriceLabel());
            if (productCard.getPriceLabel().exists()){
                break;
            }
            pagination.scrollRight().shouldBe(Condition.visible).click();
        }
        System.out.println(productCard.getTitle().scrollTo().shouldBe(Condition.visible).getText());
    }

    public void setCheckBox(String categoryName, String filterName){
        ListingViewSwitcher listingViewSwitcher = listingPage.getListingViewSwitcher();
        listingViewSwitcher.setGridView();

        Filter filter = listingPage.getFilter(categoryName, filterName);
        filter.getCheckBox().click();
    }


}
