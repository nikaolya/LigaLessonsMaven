package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

// Общий класс одновременно для check box и toggle button
public class Filter {
    private SelenideElement checkBox;
    private SelenideElement checkBoxState;
    private SelenideElement toggleButton;

    // Check-box
    private final String XPATH_FILTER_CONTAINER = "//mvid-accordion[contains(@class, 'accordion-block')]";
    private final String XPATH_NESTED_CATEGORY_NAME = "//label[.//span[text() = ' %s ']]";
    private final String XPATH_NESTED_FILTER_NAME = "/following-sibling::div//a[text() = ' %s ']";
    // Toggle button
    private final String XPATH_TOGGLE_BUTTON = "//div[contains(@class, 'switcher-block')]//a[text() = ' %s ']";


    // Check-box
    private Filter(String categoryName, String filterName){
        String titleContainerPath = String.format(XPATH_FILTER_CONTAINER + XPATH_NESTED_CATEGORY_NAME, categoryName);
        String xpathCheckBox = titleContainerPath + String.format(XPATH_NESTED_FILTER_NAME, filterName);
        checkBox = Selenide.$x(xpathCheckBox);
        checkBoxState = Selenide.$x(xpathCheckBox + "/../preceding-sibling::mvid-icon");
    }

    static Filter getFilter(String categoryName, String filterName) {
        return new Filter(categoryName, filterName);
    }

    public SelenideElement getCheckBox() {
        return checkBox;
    }

    public SelenideElement getCheckBoxState() {
        return checkBoxState;
    }

    // Toggle button
    private Filter (String filterName){
        String titleContainerPath = String.format(XPATH_TOGGLE_BUTTON, filterName);
        toggleButton = Selenide.$x(titleContainerPath);
    }

    static Filter getFilter(String filterName){
        return new Filter(filterName);
    }

    public SelenideElement getToggleButton() {
        return toggleButton;
    }
}
