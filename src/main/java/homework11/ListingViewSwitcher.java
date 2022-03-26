package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

// Переключатель вида отображения товаров на странице
public class ListingViewSwitcher {
    private SelenideElement viewSwitcher;
    private SelenideElement viewState;

    public ListingViewSwitcher(){
        viewSwitcher = Selenide.$x("//div[@class = 'listing-view-switcher']//button");
        viewState = Selenide.$x("//div[contains(@class, 'listing-view-switcher__pointer')]");
    }

    static ListingViewSwitcher getListingViewSwitcher() {
        return new ListingViewSwitcher();
    }

    public SelenideElement getViewSwitcher() {
        return viewSwitcher;
    }

    public SelenideElement getViewState() {
        return viewState;
    }

}
