package final_task.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class Pagination {

    private SelenideElement scrollLeft;
    private SelenideElement scrollRight;
    private SelenideElement[] paginationButtons;
    private SelenideElement currentPage;

    private final String XPATH_PAGINATION_CONTAINER = "//div[contains(@class,  'bottom-controls')]//ul[contains(@class, 'pagination')]";

    public Pagination(){
        scrollRight = Selenide.$x(XPATH_PAGINATION_CONTAINER + "/li[last()]/a");
        scrollLeft = Selenide.$x(XPATH_PAGINATION_CONTAINER + "/li[1]/a");

        // Определение количества страниц в разделе и их заполнение
        SelenideElement lastPage = Selenide.$x(XPATH_PAGINATION_CONTAINER + "/li[last()-1]/a");
        int numberOfPages = Integer.valueOf(lastPage.getText());
        paginationButtons = new SelenideElement[numberOfPages+1];
        for (int i = 1; i <= numberOfPages; i++) {
            String path = String.format("/li[a[text() = '%d']]", i);
            paginationButtons[i] = Selenide.$x(XPATH_PAGINATION_CONTAINER + path);
        }
        // Определение текущей страницы
        currentPage = Selenide.$x(XPATH_PAGINATION_CONTAINER + "/li/span");
    }

    static Pagination getPagination() {
        return new Pagination();
    }

    public void scrollRight(){
        scrollRight.should(Condition.exist);
        scrollRight.click();
    }

}
