package homework11;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class Pagination {

    private SelenideElement scrollLeft;
    private SelenideElement scrollRight;
    private SelenideElement[] paginationButtons= new SelenideElement [8];
    private SelenideElement currentPage;

    private final String XPATH_PAGINATION_CONTAINER = "//div[contains(@class,  'bottom-controls')]";

    private Pagination(){
        scrollRight = Selenide.$x(XPATH_PAGINATION_CONTAINER + "//ul[contains(@class, 'pagination')]/li[last()]/a");
        scrollLeft = Selenide.$x(XPATH_PAGINATION_CONTAINER + "//ul[contains(@class, 'pagination')]/li[1]/a");
        for (int i = 1; i < paginationButtons.length; i++) {
            String path = String.format("//ul[contains(@class, 'pagination')]/li[a[text() = '%d']]", i);
            paginationButtons[i] = Selenide.$x(XPATH_PAGINATION_CONTAINER + path);
        }
        currentPage = Selenide.$x(XPATH_PAGINATION_CONTAINER + "//ul[contains(@class, 'pagination')]/li/span");
    }

    static Pagination getPagination() {
        return new Pagination();
    }

    public SelenideElement scrollRight() {
        return this.scrollRight;
    }

    public SelenideElement scrollLeft() {
        return this.scrollLeft;
    }

    public SelenideElement[] getPaginationButtons() {
        return paginationButtons;
    }

    public SelenideElement getCurrentPage() {
        return currentPage;
    }
}
