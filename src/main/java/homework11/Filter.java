package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class Filter {
    private SelenideElement checkBox;

    private final String XPATH_FILTER_CONTAINER = "//mvid-accordion[contains(@class, 'accordion-block')]";
    private final String XPATH_NESTED_CATEGORY_NAME = "//label[.//span[text() = ' %s ']]";
    private final String XPATH_NESTED_FILTER_NAME = "/following-sibling::div//a[text() = ' %s ']";

    private Filter(String categoryName, String filterName){
        String titleContainerPath = String.format(XPATH_FILTER_CONTAINER + XPATH_NESTED_CATEGORY_NAME, categoryName);
        checkBox = Selenide.$x(titleContainerPath + String.format(XPATH_NESTED_FILTER_NAME, filterName));
    }

    static Filter getFilter(String categoryName, String filterName) {
        return new Filter(categoryName, filterName);
    }

    public SelenideElement getCheckBox() {
        return checkBox;
    }
}
