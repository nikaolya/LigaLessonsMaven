package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class SelectCityModalWindow {
    private final String XPATH_WINDOW = "//div[contains(@class, 'modal-layout')]";
    private final String XPATH_NESTED_TITLE = "/h3";
    private final String XPATH_CITY_OPTIONS = "//div[contains(@class, 'modal-layout__content')]//ul[contains(@class, 'location-select__location-list')]/li[text() = \"%s\"]";

    private SelenideElement title;
    private SelenideElement window;

    public SelectCityModalWindow(){
        title = Selenide.$x(XPATH_WINDOW+XPATH_NESTED_TITLE);
        window = Selenide.$x(XPATH_WINDOW);
    }

    public String getSelectCityModalWindowTitle(){
        title.should(Condition.exist);
        return title.getText();
    }

    public void setCity(String cityTitle){
        SelenideElement city = Selenide.$x(String.format(XPATH_CITY_OPTIONS,cityTitle));
        city.click();
    }

    public boolean windowIsDisplayed(){
        return window.isDisplayed();
    }


}
