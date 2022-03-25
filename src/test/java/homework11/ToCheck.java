package homework11;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import static homework11.PageObject.scrollDown;

public class ToCheck {

    public static void main(String[] args) throws Exception {
        PageObject listingPage = new PageObject();
        Selenide.open("https://www.mvideo.ru/stiralnye-i-sushilnye-mashiny-2427/stiralnye-mashiny-89/f/category=uzkie-stiralnye-mashiny-2446?from=under_search");
        ListingViewSwitcher listingViewSwitcher = new ListingViewSwitcher();
        listingViewSwitcher.setGridView();

        //ProductCard productCard = listingPage.getProductCard("Smart-TV приставка Xiaomi Mi Box S EU (MDZ-22-AB)");

        //System.out.println(productCard.getOldPriceLabel().getText());
        //System.out.println(productCard.getStarRating().getText());
        //System.out.println(productCard.getReviews().getText());
        //productCard.getReviews().click();
        //System.out.println(productCard.getmBonusValue().getText());
        //productCard.getBonusRubles().hover();

        Pagination pagination = listingPage.getPagination();
        pagination.scrollRight().shouldBe(Condition.visible).click();
        ProductCard productCard = listingPage.getProductCard("Стиральная машина узкая Indesit BWSE 61051");
        //Selenide.sleep(1000);

        System.out.println(scrollDown(productCard.getPriceLabel()).scrollTo().shouldBe(Condition.visible).getText());
        //pagination.scrollLeft().click();
        pagination.getPaginationButtons()[5].shouldBe(Condition.visible).click();
        ProductCard productCard2 = listingPage.getProductCard("Стиральная машина узкая Whirlpool FWSD71283WCV RU.1");
        //Selenide.sleep(1000);
        System.out.println(scrollDown(productCard2.getReviews()).scrollTo().shouldBe(Condition.visible).getText());
        //System.out.println(pagination.getCurrentPage().scrollTo().shouldBe(Condition.visible).getText());

    }
}
