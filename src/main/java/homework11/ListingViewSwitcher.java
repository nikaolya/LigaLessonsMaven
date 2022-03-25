package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

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

    public void setGridView(){
        String listingType = getViewState().getAttribute("class");
        if (listingType.contains("listing-view-switcher__pointer--list")){
            getViewSwitcher().click();
        }
    }

    public void setListView(){
        String listingType = getViewState().getAttribute("class");
        if (listingType.contains("listing-view-switcher__pointer--grid")){
            getViewSwitcher().click();
        }
    }


}
